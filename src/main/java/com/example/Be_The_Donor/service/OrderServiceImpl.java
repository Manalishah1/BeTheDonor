package com.example.Be_The_Donor.service;

import com.example.Be_The_Donor.entity.ApplicationUser;
import com.example.Be_The_Donor.entity.Orders;
import com.example.Be_The_Donor.entity.OrderItem;
import com.example.Be_The_Donor.entity.Product;
import com.example.Be_The_Donor.exception.ErrorResponse;
import com.example.Be_The_Donor.exception.ResourceNotFoundException;
import com.example.Be_The_Donor.repository.OrderItemsRepository;
import com.example.Be_The_Donor.repository.OrderRepository;
import com.example.Be_The_Donor.repository.ProductRepository;
import com.example.Be_The_Donor.repository.UserRepository;
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

    @Override
    public Boolean addOrder(JSONObject payload) {
        JSONObject jsonObj = payload;
        Long userId = null;
        userId = ((Number) jsonObj.get("userId")).longValue();
        ApplicationUser user = new ApplicationUser();
        boolean userExists = userRepository.existsById(userId);
        if (userExists) {
            user = userRepository.getById(userId);
        }
        else {
            throw new ResourceNotFoundException("User with User ID: "+ userId +" is not present.");
        }
        Orders newOrder = new Orders();
        newOrder.setUserId(user);
        Double total = Double.parseDouble((String) jsonObj.get("total"));
        newOrder.setTotal(total);
        orderRepository.save(newOrder);
        Long orderId = newOrder.getOrderId();
        Orders order = orderRepository.getById(orderId);

        ArrayList<HashMap<String,String>> jsonList = (ArrayList) payload.get("order");
        for (HashMap<String,String> map:
                jsonList) {
            OrderItem newOrderItems = new OrderItem();
            newOrderItems.setOrderId(order);
            Long productId = Long.parseLong(map.get("productId"));
            Product product = new Product();

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
    public List<Orders> getOrders() {
        Long id = Long.valueOf(1);
        boolean userExists = userRepository.existsById(id);
        ApplicationUser user = new ApplicationUser();
        if (userExists) {
            user = userRepository.getById(id);
        }
        List<Orders> orders = orderRepository.findByUserId(user);
        System.out.println(orders.size());
        return orders;
    }

    @Override
    public List<OrderItem> getOrderItems(Orders orders) {
        List<OrderItem> orderItems = null;
        orderItems = orderItemsRepository.findByOrderId(orders);
        System.out.println(orderItems.size());
        return orderItems;
    }
}