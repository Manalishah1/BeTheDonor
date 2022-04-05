package com.beTheDonor.service;

import com.beTheDonor.entity.ApplicationUser;
import com.beTheDonor.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class AdminServiceTest {
    // creating a mock object of the class ProductRepository
    @Mock
    private UserRepository userRepository;
    // calling junit
    @InjectMocks
    ApplicationUserService service;

    //    Creating mock product with the name "Dummy", with price 200 and with quantity 5

    @Test
    void getUsers() {
        List<ApplicationUser> listUser = new ArrayList<ApplicationUser>();
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setFirstname("firstName");
        listUser.add(applicationUser);
//		Checking with dummy data,if it exists, then returning the list of products
        when(userRepository.findAll()).thenReturn(listUser);
//      Checking with dummy data and checking to see if it not null and has at least 1 product
        List<ApplicationUser> userResp = service.findAll();
        assertThat(userResp).isNotNull();
        assertThat(userResp).hasSize(1);
    }

    @Test
    void getPatients() {
        List<ApplicationUser> listUser = new ArrayList<ApplicationUser>();
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setFirstname("firstName");
        listUser.add(applicationUser);
//		Checking with dummy data,if it exists, then returning the list of products
        when(userRepository.getPatient()).thenReturn(listUser);
//      Checking with dummy data and checking to see if it not null and has at least 1 product
        List<ApplicationUser> userResp = service.getPatients();
        assertThat(userResp).isNotNull();
        assertThat(userResp).hasSize(1);
    }

    @Test
    void getRiders() {
        List<ApplicationUser> listUser = new ArrayList<ApplicationUser>();
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setFirstname("firstName");
        listUser.add(applicationUser);
//		Checking with dummy data,if it exists, then returning the list of products
        when(userRepository.getRider()).thenReturn(listUser);
//      Checking with dummy data and checking to see if it not null and has at least 1 product
        List<ApplicationUser> userResp = service.getRider();
        assertThat(userResp).isNotNull();
        assertThat(userResp).hasSize(1);
    }

    @Test
    void getDonors() {
        List<ApplicationUser> listUser = new ArrayList<ApplicationUser>();
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setFirstname("firstName");
        listUser.add(applicationUser);
//		Checking with dummy data,if it exists, then returning the list of products
        when(userRepository.getDonor()).thenReturn(listUser);
//      Checking with dummy data and checking to see if it not null and has at least 1 product
        List<ApplicationUser> userResp = service.getDonor();
        assertThat(userResp).isNotNull();
        assertThat(userResp).hasSize(1);
    }

    @Test
    void add() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}