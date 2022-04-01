package com.example.Be_The_Donor.service;

import com.example.Be_The_Donor.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/*@AllArgsConstructor*/
public class AdminService {
    @Autowired
    ApplicationUserService ap;
    public List<ApplicationUser> getUsers() {
        return ap.findAll();
    }
    public List<ApplicationUser> getPatients(){ return ap.getPatients(); }
}
