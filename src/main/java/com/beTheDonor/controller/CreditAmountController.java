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

    @RequestMapping("/updateTipPercent/{tipPercent}")
    @ResponseBody
    void updateTip(@PathVariable Double tipPercent){
        creditAmountService.updateTipPercent(tipPercent);
    }

    @RequestMapping("/getTipPercent")
    @ResponseBody
    Double getTip() {
        return creditAmountRepository.getById(1).getRiderTipPercent();
    }
}
