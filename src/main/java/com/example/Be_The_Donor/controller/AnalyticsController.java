package com.example.Be_The_Donor.controller;

import java.util.List;

import com.example.Be_The_Donor.entity.Donors;
import com.example.Be_The_Donor.entity.Patients;
import com.example.Be_The_Donor.entity.Riders;
import com.example.Be_The_Donor.entity.TotalAmount;
import com.example.Be_The_Donor.repository.DonorRepository;
import com.example.Be_The_Donor.repository.PatientRepository;
import com.example.Be_The_Donor.repository.RiderRepository;
import com.example.Be_The_Donor.service.AnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {
//	Creating the reference of AnalyticsService
	private AnalyticsService analyticsService;
	private final PatientRepository patientRepository;
	private final RiderRepository riderRepository;
	private final DonorRepository donorRepository;

	public AnalyticsController(AnalyticsService analyticsService, PatientRepository patientRepository, RiderRepository riderRepository, DonorRepository donorRepository) {
		super();
		this.analyticsService = analyticsService;
		this.patientRepository = patientRepository;
		this.riderRepository = riderRepository;
		this.donorRepository = donorRepository;
	}
	
//	making call to the api /api/v1/analytics/patients and checking if we are getting success response (200 OK) or not
	@GetMapping("/patients")
	public ResponseEntity<List<Patients>> getAllHelpedPatients(){
		return new ResponseEntity<List<Patients>>(analyticsService.getAllHelpedPatients(), HttpStatus.OK);
		
	}
//	making call to the api /api/v1/analytics/donors and checking if we are getting success response (200 OK) or not
	@GetMapping("/donors")
	public ResponseEntity<List<Donors>> getAllDonorsWhoHelped(){
		return new ResponseEntity<List<Donors>>(analyticsService.getAllDonorsWhoHelped(), HttpStatus.OK);
		
	}
//	making call to the api /api/v1/analytics/riders and checking if we are getting success response (200 OK) or not
	@GetMapping("/riders")
	public ResponseEntity<List<Riders>> getAllRidersWhoDeliver(){
		return new ResponseEntity<List<Riders>>(analyticsService.getAllRidersWhoDeliver(), HttpStatus.OK);
		
	}
//	making call to the api /api/v1/analytics/total and checking if we are getting success response (200 OK) or not
	@GetMapping("/total")
	public ResponseEntity<TotalAmount> getAllProducts(){
		TotalAmount totalAmount = new TotalAmount();
		totalAmount.setTotalAmount(analyticsService.totalAmountOfHelp());
		return new ResponseEntity<TotalAmount>(totalAmount, HttpStatus.OK);
		
	}

	@GetMapping("")
	public String getAnalytics(Model model){
		List<Patients> PatientsModel =patientRepository.findAll();
		List<Donors> DonorModel = donorRepository.findAll();
		List<Riders> RiderModel = riderRepository.findAll();
		model.addAttribute("patients", PatientsModel);
		model.addAttribute("donors",DonorModel);
		model.addAttribute("riders",RiderModel);
		return "analyticsdashboard";
	}

}
