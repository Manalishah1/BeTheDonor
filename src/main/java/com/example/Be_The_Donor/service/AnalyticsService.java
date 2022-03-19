package com.example.Be_The_Donor.service;

import com.example.Be_The_Donor.entity.Donors;
import com.example.Be_The_Donor.entity.Patients;
import com.example.Be_The_Donor.entity.Riders;

import java.util.List;

// interface containing the methods whose implementation is defined in AnalyticsServiceImpl
public interface AnalyticsService {
	List<Patients> getAllHelpedPatients();
	List<Donors> getAllDonorsWhoHelped();
	List<Riders> getAllRidersWhoDeliver();
	Double totalAmountOfHelp();

}
