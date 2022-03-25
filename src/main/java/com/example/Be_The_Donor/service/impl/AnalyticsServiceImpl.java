package com.example.Be_The_Donor.service.impl;

import java.util.List;

import com.example.Be_The_Donor.entity.Donors;
import com.example.Be_The_Donor.entity.Patients;
import com.example.Be_The_Donor.entity.Riders;
import com.example.Be_The_Donor.repository.DonorRepository;
import com.example.Be_The_Donor.repository.PatientRepository;
import com.example.Be_The_Donor.repository.RiderRepository;
import com.example.Be_The_Donor.service.AnalyticsService;
import org.springframework.stereotype.Service;



@Service
public class AnalyticsServiceImpl implements AnalyticsService {
	
	private PatientRepository patientRepository;
	private DonorRepository donorRepository;
	private RiderRepository riderRepository;


	public AnalyticsServiceImpl(PatientRepository patientRepository, DonorRepository donorRepository, RiderRepository riderRepository) {
		this.patientRepository = patientRepository;
		this.donorRepository = donorRepository;
		this.riderRepository = riderRepository;
	}

	@Override
	public List<Patients> getAllHelpedPatients() {
		return patientRepository.findAllByIshelped(true);
	}

	@Override
	public List<Donors> getAllDonorsWhoHelped() {
		return donorRepository.findAllByHelpDone(true);
	}

	@Override
	public List<Riders> getAllRidersWhoDeliver() {
		return riderRepository.findAllByDelivery(true);
	}

	@Override
	public Double totalAmountOfHelp() {
		return donorRepository.selectTotals();
	}

	
	

}
