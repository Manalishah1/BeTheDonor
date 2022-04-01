package com.example.Be_The_Donor.controller;

import com.example.Be_The_Donor.entity.ApplicationUser;
import com.example.Be_The_Donor.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1" )
@AllArgsConstructor
public class ApplicationUserController {
    /*@Autowired*/
    AdminService adminService;
    @GetMapping(path = "/getUsers")
    public ResponseEntity<List<ApplicationUser>> getUsers(){
        return new ResponseEntity<List<ApplicationUser>>(adminService.getUsers(), HttpStatus.OK);
    }
    @GetMapping(path = "/getPatients")
    public ResponseEntity<List<ApplicationUser>> getPatients(){
        return new ResponseEntity<List<ApplicationUser>>(adminService.getPatients(), HttpStatus.OK);
    }
    @GetMapping(path = "/getRiders")
    public ResponseEntity<List<ApplicationUser>> getRiders(){
        return new ResponseEntity<List<ApplicationUser>>(adminService.getRiders(), HttpStatus.OK);
    }
}
