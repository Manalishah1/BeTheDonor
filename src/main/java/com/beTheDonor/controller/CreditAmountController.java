package com.beTheDonor.controller;

import com.beTheDonor.repository.CreditAmountRepository;
import com.beTheDonor.service.CreditAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/autoPayByCredit")
public class CreditAmountController {

    @Autowired
    CreditAmountService creditAmountService;

    @Autowired
    CreditAmountRepository creditAmountRepository;

    @RequestMapping("/updateTip/{tip}")
    @ResponseBody
    void updateTip(@PathVariable Double tip){
        creditAmountService.updateTipAmount(tip);
    }

    @RequestMapping("/getTip")
    @ResponseBody
    Double getTip() {
        return creditAmountRepository.getById(1).getRiderTip();
    }
}
