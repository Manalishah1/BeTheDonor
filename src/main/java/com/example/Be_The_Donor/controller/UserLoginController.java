package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.config.PasswordEncoder;
import com.example.Be_The_Donor.entity.ApplicationUser;
import com.example.Be_The_Donor.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class UserLoginController {

    @Autowired
    ApplicationUserService applicationUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/Test", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUser(HttpServletResponse httpServletResponse , @RequestBody ApplicationUser user) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {


            ApplicationUser applicationUser = applicationUserService.findByEmail(user.getEmail()).orElse(null);

            boolean isPasswordMatch = passwordEncoder.bCryptPasswordEncoder().matches(user.getPassword(),applicationUser.getPassword());
            if(isPasswordMatch) {
                System.out.println("check");
                if (applicationUser.getEmail().equals(user.getEmail())) {
                    response.put("data", "Login Successfully Done");
                    httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
                }
            }
            else{
                System.out.println("incorrect username or password");
            }

        } catch (Exception ex) {
            response.put("error", ex.getMessage());
        }
        return response;
    }


    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkAuthentication() {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("data", "Login Successful for USER");

        }catch (Exception ex){
            response.put("error", ex.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/loginSuccess1", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkAuthentication1() {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("data", "Login Successful FOR ADMIN");

        }catch (Exception ex){
            response.put("error", ex.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> logoutSuccessful() {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("data", "Logout Successful");

        }catch (Exception ex){
            response.put("error", ex.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> accessdenied() {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("data", "access denied");

        }catch (Exception ex){
            response.put("error", ex.getMessage());
        }
        return response;
    }

}
