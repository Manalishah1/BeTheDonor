package com.example.Be_The_Donor.repository;

import com.example.Be_The_Donor.entity.Riders;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class RiderRepositoryTest
{
    @Autowired
    private RiderRepository riderRepository;

    @Test
    @Order(1)
    @DisplayName("Testing riders method in repository layer")
    @Rollback(value = false)
    public void saveRidersTest() {
        Riders riders = Riders.builder().id(1L).driverName("jayshree").age(28L).delivery(true).build();
        riderRepository.save(riders);
        Assertions.assertThat(riders.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @DisplayName("Testing findAllByDelivery() method in repository layer")
    @Rollback(value = false)
    public void findAllByDeliveryTest() {
        Riders riders = new Riders();
        List<Riders> ridersList = riderRepository.findAllByDelivery(true);
        if(ridersList.size()>0){
            riders = ridersList.get(0);
        }
        Assertions.assertThat(riders.getDriverName().equals("jayshree"));
    }
}