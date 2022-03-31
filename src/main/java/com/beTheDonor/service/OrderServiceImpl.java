package com.beTheDonor.service;

import com.beTheDonor.entity.*;
import com.beTheDonor.repository.*;
import com.example.Be_The_Donor.entity.*;
import com.beTheDonor.exception.ResourceNotFoundException;
import com.example.Be_The_Donor.repository.*;
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

    @Override
    public Boolean addOrder(JSONObject payload) {
        JSONObject jsonObj = payload;
        Long userId;
        userId = ((Number) jsonObj.get("userId")).longValue();
        ApplicationUser user;
        boolean userExists = userRepository.existsById(userId);
        if (userExists) {
            user = userRepository.getById(userId);
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
        return true;
    }

    @Override
    public List<Orders> getOrdersByUserId(Long userId) {
        boolean userExists = userRepository.existsById(userId);
        ApplicationUser user = new ApplicationUser();
        if (userExists) {
            user = userRepository.getById(userId);
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
    public List<OrderResponse> getOrdersResponseByUserId(Long userId) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Orders> orders = getOrdersByUserId(userId);
        return formOrderResponses(orderResponses, orders);
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> orders = orderRepository.findAll();
        return orders;
    }

    @Override
    public List<OrderResponse> getOrderResponse() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        List<Orders> orders = getAllOrders();
        return formOrderResponses(orderResponses, orders);
    }

    private List<OrderResponse> formOrderResponses(List<OrderResponse> orderResponses, List<Orders> orders) {
        for (int i = 0; i < orders.size(); i++) {
            OrderResponse orderResponse;
            List<OrderItem> orderItem = getOrderItems(orders.get(i));
            List<String> productName = new ArrayList<>();
            List<Double> price = new ArrayList<>();
            List<Integer> quantity = new ArrayList<>();

            orderResponse = new OrderResponse();
            orderResponse.setOrderId(orders.get(i).getOrderId());
            orderResponse.setTotalAmount(orders.get(i).getTotal());
            orderResponse.setFirstName(orders.get(i).getUserId().getFirstname());
            orderResponse.setLastName(orders.get(i).getUserId().getLastname());

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