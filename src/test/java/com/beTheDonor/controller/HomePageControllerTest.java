package com.beTheDonor.controller;

import com.beTheDonor.service.ApplicationUserService;
import com.beTheDonor.service.impl.RiderService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(controllers = HomePageController.class)
@AutoConfigureMockMvc
class HomePageControllerTest {

    private MockMvc mockMvc;

    @Autowired
    HomePageController homePageController;

    @MockBean
    ApplicationUserService applicationUserService;

    @MockBean
    BCryptPasswordEncoder passwordEncoder;




    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(homePageController).build();
    }

    @Test
    @DisplayName("Testing home page")
    @Order(1)
    void homePage() throws Exception {
        mockMvc.perform(get("/homePage")).andDo(print()).andExpect(view().name("HomePage"));
    }
}