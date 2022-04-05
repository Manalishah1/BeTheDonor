/*
package com.beTheDonor.controller;

import com.beTheDonor.config.WebSecurityConfiguration;
import com.beTheDonor.entity.ApplicationUser;
import com.beTheDonor.service.ApplicationUserService;
import com.beTheDonor.service.DonorService;
import com.beTheDonor.service.impl.DonorServiceImpl;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import javax.validation.Payload;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(controllers = DonorControllerTest.class)

public class DonorControllerTest {
    @Autowired
    DonorController donorController;
    @MockBean
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DonorService donorService;
    @MockBean
    private WebSecurityConfiguration webSecurityConfiguration;
    @MockBean
    private ApplicationUser applicationUser;
    @MockBean
    private DaoAuthenticationProvider authenticationManager;
    @MockBean
    private ApplicationUserService userDetailsService;

    @Autowired
    UserLoginController userLoginController;

    @BeforeEach
    void setUp() {
        userLoginController = Mockito.spy(userLoginController);
        mockMvc = MockMvcBuilders.standaloneSetup(userLoginController).build();
    }
    @Test
    @DisplayName("success request testing")
    @Order(1)
    void testChangeStatusAfterOrder() throws Exception {
        Authentication authentication = new UsernamePasswordAuthenticationToken("dhsoni2510@gmail.com","123456", AuthorityUtils.createAuthorityList("Donor"));

        Mockito.when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        Mockito.when(userDetailsService.loadUserByUsername(anyString())).thenReturn(new User("dhsoni2510@gmail.com","123456", AuthorityUtils.createAuthorityList("Donor")));

        mockMvc.perform(post("/api/v1/authenticate")
                        .param("email","dhsoni2510@gmail.com")
                        .param("password","123456"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/donorview"));

        JSONObject obj = new JSONObject();
        Mockito.doReturn(true).when(donorService).changeStatusOfOrder(obj);

    }

}
*/
