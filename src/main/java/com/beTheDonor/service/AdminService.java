package com.beTheDonor.service;
import com.beTheDonor.entity.ApplicationUser;
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
    public List<ApplicationUser> getRiders(){ return ap.getRider(); }
    public List<ApplicationUser> getDonors(){ return ap.getDonor(); }
}
