package com.beTheDonor.service.impl;

import com.beTheDonor.entity.CreditAmount;
import com.beTheDonor.entity.Orders;
import com.beTheDonor.repository.CreditAmountRepository;
import com.beTheDonor.repository.OrderRepository;
import com.beTheDonor.service.CreditAmountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CreditAmountServiceImpl implements CreditAmountService {

    @Autowired
    CreditAmountRepository creditAmountRepository;

    @Autowired
    OrderRepository orderRepository;


    @Override
    public void orderFromCredits() {
        CreditAmount creditAmount = creditAmountRepository.getById(1);
        Double currentAmount = creditAmount.getCreditAmount();
        Double tip = creditAmountRepository.getById(1).getRiderTip();
        List<Orders> orders = orderRepository.findByOrderStatusAndTotalLessThanEqual("pending payment",currentAmount-tip);
        for(int i=0;i<orders.size();i++) {
            Long orderId = orders.get(i).getOrderId();
            if(currentAmount >= orders.get(i).getTotal() + tip) {
                currentAmount -= orders.get(i).getTotal() + tip;
                creditAmount.setCreditAmount(currentAmount);
                orders.get(i).setOrderStatus("pending delivery");
                orders.get(i).setOrderPaidOn(new Date());
                creditAmountRepository.save(creditAmount);
                orderRepository.save(orders.get(i));
            }
            //rider send tip
        }
    }

    @Override
    public void updateTipAmount(Double tip) {
        CreditAmount creditAmount = creditAmountRepository.getById(1);
        creditAmount.setRiderTip(tip);
        creditAmountRepository.save(creditAmount);
    }
}
