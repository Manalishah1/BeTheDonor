package com.beTheDonor.controller;

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

    @RequestMapping("/updateTip/{tip}")
    @ResponseBody
    void getTipAmount(@PathVariable Double tip){
        creditAmountService.updateTipAmount(tip);
    }
}
