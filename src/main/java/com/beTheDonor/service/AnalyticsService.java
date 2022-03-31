package com.beTheDonor.service;

import com.beTheDonor.entity.Donors;
import com.beTheDonor.entity.Patients;
import com.beTheDonor.entity.Riders;

import java.util.List;

// interface containing the methods whose implementation is defined in AnalyticsServiceImpl
public interface AnalyticsService {
	List<Patients> getAllHelpedPatients();
	List<Donors> getAllDonorsWhoHelped();
	List<Riders> getAllRidersWhoDeliver();
	Double totalAmountOfHelp();

}
