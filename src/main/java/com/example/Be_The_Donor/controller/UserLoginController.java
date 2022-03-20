package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.config.PasswordEncoder;
import com.example.Be_The_Donor.controller.requestbody.RegistrationRequest;
import com.example.Be_The_Donor.entity.ApplicationUser;
import com.example.Be_The_Donor.enumerators.ApplicationUserRole;
import com.example.Be_The_Donor.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller

public class UserLoginController {


    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private DaoAuthenticationProvider authenticationManager;


    @Autowired
    private ApplicationUserService userDetailsService;


    @GetMapping("/api/v1/login")
    public String viewLoginPage(Model model) {
        model.addAttribute("user", new RegistrationRequest());
        return "login";
    }


    @GetMapping("/loginSuccess1")
    public String checkAuthentication() {

        return "loginSuccess";
    }


    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> logoutSuccessful() {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("data", "Logout Successful");

        } catch (Exception ex) {
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

        } catch (Exception ex) {
            response.put("error", ex.getMessage());
        }
        return response;
    }


    @RequestMapping(value = "/api/v1/authenticate", method = RequestMethod.POST)

    public String createAuthenticationToken(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") RegistrationRequest applicationUser) throws Exception {

        Authentication authentication = authenticate(applicationUser.getEmail(), applicationUser.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(applicationUser.getEmail());
        // Principal principal = request.getUserPrincipal();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute(applicationUser.getEmail(), securityContext);

        if (userDetails.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"))) {
            if (((ApplicationUser) userDetails).getType_of_user().equals("Donor")) {
                System.out.println("Donor found");
                return "redirect:/api/v1/donorview";

            }
            if (((ApplicationUser) userDetails).getType_of_user().equals("Rider")) {
                return "redirect:/loginSuccess1";
                //Add Rider API

            }
            if (((ApplicationUser) userDetails).getType_of_user().equals("Patient")) {
                return "redirect:loginSuccess";
                //Add Patient API

            }


        } else if (userDetails.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"))) {
            return "redirect:/api/v1/loginSuccess";
        } else {
            return "redirect:/accessdenied";
        }
        return "redirect:/loginSuccess1";
    }


    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
