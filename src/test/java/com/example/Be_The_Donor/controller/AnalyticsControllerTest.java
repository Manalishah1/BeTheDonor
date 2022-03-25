package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.controller.requestbody.RegistrationRequest;
import com.example.Be_The_Donor.service.AnalyticsService;
import com.example.Be_The_Donor.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(controllers = AnalyticsController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ContextConfiguration(locations = "/test-context.xml")
class AnalyticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AnalyticsController analyticsController;

    @Test
    @DisplayName("success getAllHelpedPatients test")
    @Order(1)
    void testControllerGetAllHelpedPatientsSuccessRequest() throws Exception {
        mockMvc.perform(get("/api/v1/analytics/patients")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("success getAllDonorsWhoHelped test")
    @Order(2)
    void testControllerGetAllDonorsWhoHelpedSuccessRequest() throws Exception {
        this.mockMvc.perform(get("/api/v1/analytics/donors")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("success getAllRidersWhoDeliver test")
    @Order(3)
    void testControllerGetAllRidersWhoDeliverSuccessRequest() throws Exception {
        this.mockMvc.perform(get("/api/v1/analytics/riders")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("success getAllProducts test")
    @Order(4)
    void testControllerGetAllProductsSuccessRequest() throws Exception {
        this.mockMvc.perform(get("/api/v1/analytics/total")
                        .accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk());
    }
}