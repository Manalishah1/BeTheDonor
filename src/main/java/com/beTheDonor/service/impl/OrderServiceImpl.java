package com.beTheDonor.service.impl;

import com.beTheDonor.entity.*;
import com.beTheDonor.exception.ResourceNotFoundException;
import com.beTheDonor.repository.*;
import com.beTheDonor.service.CreditAmountService;
import com.beTheDonor.service.OrderService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    CreditAmountService creditAmountService;

    @Override
    public Boolean addOrder(JSONObject payload, String userId) {
        JSONObject jsonObj = payload;
        ApplicationUser user;
        boolean userExists = userRepository.existsByEmail(userId);
        if (userExists) {
            user = userRepository.getByEmail(userId);
        }
        else {
            throw new ResourceNotFoundException("User with User ID: "+ userId +" is not present.");
        }
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        ArrayList<HashMap<String,String>> jsonAddress = (ArrayList) payload.get("address");
        for (HashMap<String,String> map:
                jsonAddress) {

            String city = map.get("city");
            deliveryAddress.setCity(city);
            String address = map.get("address");
            deliveryAddress.setAddress(address);
            String country = map.get("country");
            deliveryAddress.setCountry(country);
            String postalCode = map.get("postalCode");
            deliveryAddress.setPostalCode(postalCode);
            String province = map.get("province");
            deliveryAddress.setProvince(province);
            deliveryAddressRepository.save(deliveryAddress);
        }

        Orders newOrder = new Orders();
        newOrder.setUserId(user);
        Double total = Double.parseDouble((String) jsonObj.get("total"));
        newOrder.setTotal(total);
        newOrder.setOrderStatus("pending payment");
        newOrder.setDeliveryAddressId(deliveryAddressRepository.getById(deliveryAddress.getAddressId()));
        orderRepository.save(newOrder);
        Long orderId = newOrder.getOrderId();
        Orders order = orderRepository.getById(orderId);

        ArrayList<HashMap<String,String>> jsonList = (ArrayList) payload.get("order");
        for (HashMap<String,String> map:
                jsonList) {
            OrderItem newOrderItems = new OrderItem();
            newOrderItems.setOrderId(order);
            Long productId = Long.parseLong(map.get("productId"));
            Product product;

            boolean productExists = productRepository.existsById(productId);
            if(productExists) {
                product = productRepository.getById(productId);
            }
            else {
                throw new ResourceNotFoundException("Product with Product ID: "+productId +" is not present.");
            }
            newOrderItems.setProductId(product);
            int quantity = Integer.parseInt(map.get("quantity"));
            newOrderItems.setQuantity(quantity);
            orderItemsRepository.save(newOrderItems);

            Product updateProduct = productRepository.getById(productId);
            updateProduct.setQuantity(updateProduct.getQuantity() - quantity);
            productRepository.save(updateProduct);
        }
        creditAmountService.orderFromCredits();
        return true;
    }

    @Override
    public List<Orders> getOrdersByUserId(String userId) {
        boolean userExists = userRepository.existsByEmail(userId);
        ApplicationUser user = new ApplicationUser();
        if (userExists) {
            user = userRepository.getByEmail(userId);
        }
        List<Orders> orders = orderRepository.findByUserId(user);
        System.out.println(orders.size());
        return orders;
    }

    @Override
    public List<OrderItem> getOrderItems(Orders orders) {
        List<OrderItem> orderItems;
        orderItems = orderItemsRepository.findByOrderId(orders);
        System.out.println(orderItems.size());
        return orderItems;
    }

    @Override
    public List<PatientOrdersResponse> getOrdersResponseByUserId(String userId) {
        List<PatientOrdersResponse> orderResponses = new ArrayList<>();
        List<Orders> orders = getOrdersByUserId(userId);
        return formOrderResponses(orderResponses, orders);
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        return orders;
    }

    @Override
    public List<PatientOrdersResponse> getOrderResponse() {
        List<PatientOrdersResponse> orderResponses = new ArrayList<>();
        List<Orders> orders = getAllOrders();
        return formOrderResponses(orderResponses, orders);
    }

    private List<PatientOrdersResponse> formOrderResponses(List<PatientOrdersResponse> orderResponses, List<Orders> orders) {
        for (int i = 0; i < orders.size(); i++) {
            PatientOrdersResponse orderResponse;
            List<OrderItem> orderItem = getOrderItems(orders.get(i));
            List<String> productName = new ArrayList<>();
            List<Double> price = new ArrayList<>();
            List<Integer> quantity = new ArrayList<>();

            orderResponse = new PatientOrdersResponse();
            orderResponse.setOrderId(orders.get(i).getOrderId());
            orderResponse.setTotalAmount(orders.get(i).getTotal());
            orderResponse.setFirstName(orders.get(i).getUserId().getFirstname());
            orderResponse.setLastName(orders.get(i).getUserId().getLastname());
            if(orders.get(i).getOrderStatus().equals("pending payment")) {
                orderResponse.setOrderStatus("Order Placed");
            } else if(orders.get(i).getOrderStatus().equals("pending delivery")) {
                orderResponse.setOrderStatus("Order Paid, Pending Delivery");
            } else if(orders.get(i).getOrderStatus().equals("delivered")) {
                orderResponse.setOrderStatus("Order Delivered");
            }

            for (int j = 0; j < orderItem.size(); j++) {
                productName.add(orderItem.get(j).getProductId().getProductName());
                price.add(orderItem.get(j).getProductId().getPrice());
                quantity.add(orderItem.get(j).getQuantity());

            }
            orderResponse.setProductName(productName);
            orderResponse.setQuantity(quantity);
            orderResponse.setPrice(price);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

}