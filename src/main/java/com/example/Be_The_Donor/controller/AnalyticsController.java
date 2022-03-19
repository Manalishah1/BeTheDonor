package com.example.Be_The_Donor.controller;

import java.util.List;

import com.example.Be_The_Donor.entity.Donors;
import com.example.Be_The_Donor.entity.Patients;
import com.example.Be_The_Donor.entity.Riders;
import com.example.Be_The_Donor.entity.TotalAmount;
import com.example.Be_The_Donor.service.AnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {
//	Creating the reference of AnalyticsService
	private AnalyticsService analyticsService;

	public AnalyticsController(AnalyticsService analyticsService) {
		super();
		this.analyticsService = analyticsService;
	}
	
	
	@GetMapping("/patients")
	public ResponseEntity<List<Patients>> getAllHelpedPatients(){
		return new ResponseEntity<List<Patients>>(analyticsService.getAllHelpedPatients(), HttpStatus.OK);
		
	}
	
	@GetMapping("/donors")
	public ResponseEntity<List<Donors>> getAllDonorsWhoHelped(){
		return new ResponseEntity<List<Donors>>(analyticsService.getAllDonorsWhoHelped(), HttpStatus.OK);
		
	}
	
	@GetMapping("/riders")
	public ResponseEntity<List<Riders>> getAllRidersWhoDeliver(){
		return new ResponseEntity<List<Riders>>(analyticsService.getAllRidersWhoDeliver(), HttpStatus.OK);
		
	}
	
	@GetMapping("/total")
	public ResponseEntity<TotalAmount> getAllProducts(){
		TotalAmount totalAmount = new TotalAmount();
		totalAmount.setTotalAmount(analyticsService.totalAmountOfHelp());
		return new ResponseEntity<TotalAmount>(totalAmount, HttpStatus.OK);
		
	}

}
