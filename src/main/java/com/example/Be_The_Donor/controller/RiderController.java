package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.dto.PatientRiderDto;
import com.example.Be_The_Donor.entity.PatientDetails;
import com.example.Be_The_Donor.service.RiderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/riderDashboard")
public class RiderController
{
    RiderService riderService;

    @GetMapping("/city")
    public String getRiderDashboard(@RequestParam(value = "cityName",required = false) String cityName,Model model)
    {
        System.out.println("cityname passed in controller is: " + cityName);
        ArrayList<PatientRiderDto> patientDetails = (ArrayList<PatientRiderDto>) riderService.getByCityName(cityName);
        if(patientDetails.isEmpty())
        {
           return "redirect:/api/v1/riderDashboard?noPatient";
        }

        model.addAttribute("cityName",cityName);
       model.addAttribute("patients",patientDetails);
        return "riderDashboard";
    }

    @GetMapping("/keyword")
    public ResponseEntity<String> getRiders(@RequestParam(value = "q") String query)
    {
        List<String> strings =riderService.getCities().stream().filter(city->city.toLowerCase().contains(query)).limit(15).collect(Collectors.toList());
        System.out.println(strings);
        ObjectMapper mapper = new ObjectMapper();
        String resp = "";

        try {
            resp = mapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
        }

        return new ResponseEntity<String>(resp, HttpStatus.OK);
    }


}
