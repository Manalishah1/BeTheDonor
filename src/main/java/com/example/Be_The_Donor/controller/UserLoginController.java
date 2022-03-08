package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.config.PasswordEncoder;
import com.example.Be_The_Donor.entity.ApplicationUser;
import com.example.Be_The_Donor.security.JwtTokenUtil;
import com.example.Be_The_Donor.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserLoginController {

    @Autowired
    ApplicationUserService applicationUserService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private DaoAuthenticationProvider authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ApplicationUserService userDetailsService;

  /*  @RequestMapping(value = "/Test", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getUser(HttpServletResponse httpServletResponse, @RequestBody ApplicationUser user) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {


            ApplicationUser applicationUser = applicationUserService.findByEmail(user.getEmail()).orElse(null);

            boolean isPasswordMatch = passwordEncoder.bCryptPasswordEncoder().matches(user.getPassword(), applicationUser.getPassword());
            if (isPasswordMatch) {
                System.out.println("check");
                if (applicationUser.getEmail().equals(user.getEmail())) {
                    response.put("data", "Login Successfully Done");
                    httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
                }
            } else {
                System.out.println("incorrect username or password");
            }

        } catch (Exception ex) {
            response.put("error", ex.getMessage());
        }
        return response;
    }*/

    @GetMapping("/loginSuccess")
    public String viewLoginPage() {
        // custom logic before showing login page...

        return "loginSuccess";
    }

    @PostMapping("/loginSuccess")
    public String checkAuthentication() {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("data", "Login Successful");

        } catch (Exception ex) {
            response.put("error", ex.getMessage());
        }
        return "redirect:/loginSuccess";
    }


    @RequestMapping(value = "/loginSuccess1", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> checkAuthentication1() {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put("data", "Login Successful1");

        } catch (Exception ex) {
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


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(HttpServletRequest request, @RequestBody ApplicationUser user) throws Exception {
        Authentication authentication = authenticate(user.getEmail(), user.getPassword());
        // Principal principal = request.getUserPrincipal();

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getEmail());
        applicationUserService.loadUserByUsername(user.getEmail());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute(user.getEmail(), securityContext);
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);
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
