package com.beTheDonor.service;

import com.beTheDonor.entity.ApplicationUser;
import com.beTheDonor.entity.Donors;
import com.beTheDonor.entity.Orders;
import com.beTheDonor.exception.ResourceNotFoundException;
import com.beTheDonor.repository.DonorRepository;
import com.beTheDonor.repository.OrderRepository;
import com.beTheDonor.repository.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DonorService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DonorRepository donorRepository;
    @Autowired
    UserRepository userRepository;

    public Boolean changeStatusOfOrder(JSONObject payload) {
        ArrayList<Long> jsonAddress = (ArrayList) payload.get("orderId");
        for (int i = 0; i < jsonAddress.size(); i++) {
            Orders order;
            Long id;
            id = ((Number) jsonAddress.get(i)).longValue();
            order = orderRepository.getById(id);
            order.setOrderStatus("pending delivery");
            orderRepository.save(order);

        }

        return null;
    }

    public Boolean storeTotalAmount(JSONObject payload, Long id) {
        Donors donor = new Donors();
        boolean donorExists = donorRepository.existsByDonorId(id);
        List<Integer> totalAmount = (List<Integer>) payload.get("donationAmount");
        Integer sum = totalAmount.stream()
                .reduce(0, Integer::sum);
        if (donorExists) {
            donor = donorRepository.findByDonorId(id);
            donor.setAmount(donor.getAmount() + sum.doubleValue());
        } else {
            ApplicationUser user = userRepository.findById(id).orElse(null);
            if(user != null){
                donor.setDonorId(id);
                donor.setAmount(sum.doubleValue());
                donor.setDonorName(user.getFirstname());
               // donor.setEmail(user.getEmail());
            }
        }
        donorRepository.save(donor);
        return true;
    }




}
