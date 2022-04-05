package com.beTheDonor.service;


import com.beTheDonor.controller.requestbody.RegistrationRequest;
import com.beTheDonor.email.EmailSender;
import com.beTheDonor.entity.ApplicationUser;
import com.beTheDonor.repository.UserRepository;
import com.beTheDonor.validator.BodyValidator;
import com.beTheDonor.validator.EmailValidator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegistrationServiceTest {


    @InjectMocks
    RegistrationService registrationService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ApplicationUserService applicationUserService;
    @Mock
    private ConfirmationTokenService confirmationTokenService;
    @Mock
    private EmailSender emailSender;
    @Mock
    private BodyValidator bodyValidator;
    @Mock
    private EmailValidator emailValidator;
    private ApplicationUser Donor;
    @Mock
    private AnalyticsService analyticsService;

    @Test
    @DisplayName("Testing register method of service layer for valid user information")
    @Order(1)
    void registerValidUserTest() {
        String token = "abctoken";


        RegistrationRequest registrationRequest = new RegistrationRequest("Dharmik", "Soni",
                "dhsoni2510@gmail.com", "9029892923", "Donor", "Dharmik", "Dharmik");
        Mockito.doReturn(token).when(applicationUserService).signUpUser(any());
        Mockito.doNothing().when(emailSender).send(any(), any());
        Mockito.doReturn(true).when(bodyValidator).validate(registrationRequest);
        Mockito.doReturn(true).when(emailValidator).validate(registrationRequest.getEmail());
        Mockito.doNothing().when(analyticsService).addUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        registrationService.register(registrationRequest);
    }

    @Test
    @DisplayName("Testing register method of service layer for invalid user information")
    @Order(2)
    void registerInValidUserTest() {
        String token = "abctoken";


        RegistrationRequest registrationRequest = new RegistrationRequest("Dharmik", "Soni",
                "dhsoni2510@yahoo.com", "9029892923", "Donor", "Dharmik", "Dhar");
        Mockito.doReturn(token).when(applicationUserService).signUpUser(any());
        Mockito.doNothing().when(emailSender).send(any(), any());
        Mockito.doReturn(false).when(bodyValidator).validate(registrationRequest);
        Mockito.doReturn(false).when(emailValidator).validate(registrationRequest.getEmail());
        Mockito.doNothing().when(analyticsService).addUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
        registrationService.register(registrationRequest);


    }

}