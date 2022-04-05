package com.beTheDonor.service;

import com.beTheDonor.entity.UserConfirmationToken;
import com.beTheDonor.repository.ConfirmationTokenRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConfirmationTokenServiceTest {

    @InjectMocks
    ConfirmationTokenService confirmationTokenService;

    @Mock
    ConfirmationTokenRepository confirmationTokenRepository;

    @Mock
    UserConfirmationToken token;

    @Test
    @DisplayName("Testing save confirmation token ")
    @Order(1)
    void saveConfirmationToken()
    {


    }

    @Test
    @DisplayName("Testing getToken() method678")
    @Order(2)
    void getToken()
    {

    }

    @Test
    @DisplayName("Testing setConfirmedAt() method")
    @Order(3)
    void setConfirmedAt()
    {

    }
}