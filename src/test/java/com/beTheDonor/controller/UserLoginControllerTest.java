package com.beTheDonor.controller;

import com.beTheDonor.service.ApplicationUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(UserLoginController.class)
class UserLoginControllerTest {


    private MockMvc mockMvc;


    @Autowired
    UserLoginController userLoginController;

    @MockBean
    private DaoAuthenticationProvider authenticationManager;

    @MockBean
    private ApplicationUserService userDetailsService;

    @MockBean
    BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userLoginController = Mockito.spy(userLoginController);
        mockMvc = MockMvcBuilders.standaloneSetup(userLoginController).build();
    }

    @Test
    void checkAuthentication() throws Exception {

        Authentication authentication = new UsernamePasswordAuthenticationToken("dhsoni2510@gmail.com","123456", AuthorityUtils.createAuthorityList("Donor"));

        Mockito.when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        Mockito.when(userDetailsService.loadUserByUsername(anyString())).thenReturn(new User("dhsoni2510@gmail.com","123456", AuthorityUtils.createAuthorityList("Donor")));

        mockMvc.perform(post("/api/v1/authenticate")
                        .param("email","dhsoni2510@gmail.com")
                        .param("password","123456"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/donorview"));

    }

}