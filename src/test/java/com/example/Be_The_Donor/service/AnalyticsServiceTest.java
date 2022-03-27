package com.example.Be_The_Donor.service;

import com.example.Be_The_Donor.entity.Donors;
import com.example.Be_The_Donor.entity.Patients;
import com.example.Be_The_Donor.entity.Riders;
import com.example.Be_The_Donor.repository.DonorRepository;
import com.example.Be_The_Donor.repository.PatientRepository;
import com.example.Be_The_Donor.repository.RiderRepository;
import com.example.Be_The_Donor.service.impl.AnalyticsServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AnalyticsServiceTest {


    @InjectMocks
    AnalyticsServiceImpl analyticsService;

    @Mock
    private DonorRepository donorRepository;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private RiderRepository riderRepository;

    @Test
    @DisplayName("Testing getAllHelpedPatients Function")
    @Order(1)
    void getAllHelpedPatientsTest() {
        Mockito.doReturn(Arrays.asList(new Patients(1L,"jayshree",28L,true),new Patients(2L,"jayshree2",29L,true))).when(patientRepository).findAllByIshelped(true);
        List<Patients> patientsList = analyticsService.getAllHelpedPatients();
        Assertions.assertEquals("jayshree",patientsList.get(0).getPatientName());
    }

    @Test
    @DisplayName("Testing getAllDonorsWhoHelped Function")
    @Order(2)
    void getAllDonorsWhoHelpedTest() {
        Mockito.doReturn(Arrays.asList(new Donors(1L,"jayshree",28L,5000D,true))).when(donorRepository).findAllByHelpDone(true);
        List<Donors> donorsList = analyticsService.getAllDonorsWhoHelped();
        Assertions.assertEquals("jayshree", donorsList.get(0).getDonorName());
    }

    @Test
    @DisplayName("Testing getAllRidersWhoDeliver Function")
    @Order(3)
    void getAllRidersWhoDeliverTest() {
        Mockito.doReturn(Arrays.asList(new Riders(1L,"jayshree",28L,true))).when(riderRepository).findAllByDelivery(true);
        List<Riders> riderList = analyticsService.getAllRidersWhoDeliver();
        Assertions.assertEquals("jayshree", riderList.get(0).getDriverName());
    }

    @Test
    @DisplayName("Testing totalAmountOfHelp Function")
    @Order(4)
    void totalAmountOfHelpTest() {
        Mockito.doReturn(12000D).when(donorRepository).selectTotals();
        Double amount = analyticsService.totalAmountOfHelp();
        Assertions.assertEquals(12000D,amount);
    }
}