package com.beTheDonor.service.impl;

import java.util.List;

import com.beTheDonor.repository.DonorRepository;
import com.beTheDonor.repository.PatientRepository;
import com.beTheDonor.repository.RiderRepository;
import com.beTheDonor.service.AnalyticsService;
import com.beTheDonor.entity.Donors;
import com.beTheDonor.entity.Patients;
import com.beTheDonor.entity.Riders;
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
