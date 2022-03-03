package com.example.Be_The_Donor.controller;


import com.example.Be_The_Donor.controller.requestbody.RegistrationRequest;
import com.example.Be_The_Donor.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class UserRegistrationController
{

    private final RegistrationService registrationService;


    @RequestMapping("/api/v1/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/api/v1/registration")
    public void register(@RequestBody RegistrationRequest registrationRequest)
    {
         registrationService.register(registrationRequest);
        System.out.println("Registration email successfully sent to " + registrationRequest.getEmail());
    }

    @GetMapping("/api/v1/registration/confirm")
    public String confirm(@RequestParam("token") String token)
    {
        return registrationService.confirmToken(token);
    }

}




