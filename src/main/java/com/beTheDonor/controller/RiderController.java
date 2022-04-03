package com.beTheDonor.controller;


import com.beTheDonor.dto.PatientRiderDto;
import com.beTheDonor.dto.PatientRiderDtoListWrapper;
import com.beTheDonor.model.PatientRiderModel;
import com.beTheDonor.service.impl.RiderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/riderDashboard")
public class RiderController {
    RiderService riderService;


    @PostMapping(value = "/finalOrderRider")
    public void changeStatusAfterOrder(@RequestBody JSONObject payload) throws Exception {

    }

    @GetMapping("/city")
    public String getRiderDashboard(@RequestParam(value = "cityName",required = false) String cityName,Model model)
    {
        System.out.println("cityname passed in controller is: " + cityName);
        ArrayList<PatientRiderModel> patientDetails = (ArrayList<PatientRiderModel>)riderService.getByCityName(cityName);
        System.out.println(patientDetails.size());


        ArrayList<PatientRiderDto> patientRiderDtoList = PatientRiderDto.convertToDto(patientDetails);
        System.out.println("Dto size is: " + patientRiderDtoList.size());

        if(patientRiderDtoList.isEmpty())
        {
            return "redirect:/riderDashboard?noPatient";
        }
        model.addAttribute("cityName",cityName);
        PatientRiderDtoListWrapper chosenOrders = new PatientRiderDtoListWrapper();
        chosenOrders.setPatientRiderDto(patientRiderDtoList);
        model.addAttribute("chosenOrders",chosenOrders);
        model.addAttribute("patients",patientRiderDtoList);
        return "riderDashboard";
    }

    @GetMapping("/keyword")
    public ResponseEntity<String> getRiders(@RequestParam(value = "q") String query)
    {
        List<String> strings =riderService.getCities().stream().filter(city->city.toLowerCase().contains(query)).limit(20).collect(Collectors.toList());
        System.out.println("Cities are: " + strings);
        ObjectMapper mapper = new ObjectMapper();
        String resp = "";

        try {
            resp = mapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
        }

        return new ResponseEntity<String>(resp, HttpStatus.OK);
    }

    @PostMapping("/selected")
    public void getSelected( @ModelAttribute("chosenOrders") PatientRiderDtoListWrapper chosenOrders,BindingResult result, Model model)
    {
        System.out.println("-------------"+chosenOrders.getPatientRiderDto().size());
    }


}
