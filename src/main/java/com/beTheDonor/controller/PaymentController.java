package com.beTheDonor.controller;

import com.beTheDonor.entity.Response;
import com.beTheDonor.service.AnalyticsService;
import com.beTheDonor.service.StripeService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class PaymentController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String API_PUBLIC_KEY;

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Autowired
    AnalyticsService analyticsService;

    private StripeService stripeService;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    boolean status = false;
    String transactionId = "";

    @PostMapping("/api/v1/create-charge")
    public @ResponseBody Response createCharge(String email, String token, String amount) {
        if (token == null) {
            return new Response(false, "Stripe payment token is missing. please try again later.");
        }
        String chargeId = stripeService.createCharge(email, token, Double.parseDouble(amount)*100);// 9.99 usd
        if (chargeId == null) {
            status = false;
            transactionId = "0";
            return new Response(false, "An error occurred while trying to charge.");
        }

        status = true;
        transactionId= chargeId;
        return new Response(true, chargeId);
    }

    @GetMapping("/paymentSuccess")
    public String donorLogin(Model model) {
        model.addAttribute("status", status);
        model.addAttribute("statusText", status?"Success":"Failed");
        model.addAttribute("chargeId", transactionId);
        return "transactionSuccess";
    }
}
