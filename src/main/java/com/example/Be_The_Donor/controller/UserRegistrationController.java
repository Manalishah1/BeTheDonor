package com.example.Be_The_Donor.controller;


import com.example.Be_The_Donor.controller.requestbody.RegistrationRequest;
import com.example.Be_The_Donor.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class UserRegistrationController
{
    private final RegistrationService registrationService;

    @GetMapping("/api/v1/registration")
    public String registration(Model model)
    {
        model.addAttribute("user", new RegistrationRequest());
        return "registration";
    }

    @PostMapping("/api/v1/registration")
    public String register(@Valid @ModelAttribute("user") RegistrationRequest registrationRequest, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "registration";
        }
        System.out.println("firstname is: "+ registrationRequest.getFirstName());
        registrationService.register(registrationRequest);
        System.out.println("Registration email successfully sent to the mail " + registrationRequest.getEmail());
        return "redirect:/api/v1/registration?emailSent";
    }

    @GetMapping("/api/v1/registration/confirm")
    public String confirm(@RequestParam("token") String token)
    {
        return registrationService.confirmToken(token);
    }

}




