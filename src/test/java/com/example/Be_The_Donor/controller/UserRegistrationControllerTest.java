package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.controller.requestbody.RegistrationRequest;
import com.example.Be_The_Donor.service.ApplicationUserService;
import com.example.Be_The_Donor.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(controllers = UserRegistrationController.class)
class UserRegistrationControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRegistrationController userRegistrationController;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistrationService registrationService;
    @MockBean
    private ApplicationUserService applicationUserService;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @MockBean
    private Model model;

    @MockBean
    BindingResult bindingResult;

    @Test
    @DisplayName("success request testing")
    @Order(1)
    void testControllerSuccessRequest() throws Exception {

        RegistrationRequest registrationRequest = new RegistrationRequest("Dharmik","Soni","dhsoni2510@gmail.com","9029892923","Donor","Dharmik","Dharmik");
        Mockito.doNothing().when(registrationService).register(registrationRequest);
        userRegistrationController.register(registrationRequest,bindingResult);


    }

    @Test
    @DisplayName("Bad request testing")
    @Order(2)
    void testControllerBadRequest() throws Exception {

        RegistrationRequest registrationRequest = new RegistrationRequest("Dharmik","Soni","dhsoni2510@gmail.com",null,"Donor","Dharmik","Dharmik");
        Mockito.doNothing().when(registrationService).register(registrationRequest);
        String url = "/api/v1/registration";
        mockMvc.perform(post(url).content(objectMapper.writeValueAsString(registrationRequest))).andExpect(model().hasErrors());
        userRegistrationController.register(registrationRequest,bindingResult);


    }


    @Test
    @DisplayName("GET Registration Page testing")
    @Order(3)
    void registrationGETTest()
    {
        userRegistrationController.registration(model);
    }


    @Test
    @DisplayName("Confirm Token testing")
    @Order(4)
    void confirmTest() throws Exception {

        String page = "confirmation";
        Mockito.doReturn(page).when(registrationService).confirmToken(any());
        userRegistrationController.confirm(any());
    }
}