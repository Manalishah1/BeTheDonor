package com.beTheDonor.controller.pages;

import com.beTheDonor.entity.CreditAmount;
import com.beTheDonor.repository.CreditAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DonateByAmount {

    @Autowired
    CreditAmountRepository creditAmountRepository;

    @RequestMapping(value = "/donate")
    public String donateByAmount(Model model) {
            CreditAmount creditAmount = creditAmountRepository.getById(1);
            model.addAttribute("fundRaised", creditAmount.getFundRaised());
        return "donate";
    }

    @RequestMapping(value = "/thank-you")
    public String thankYou() {
        return "thank-you";
    }
}

