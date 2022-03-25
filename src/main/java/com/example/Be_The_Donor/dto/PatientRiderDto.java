package com.example.Be_The_Donor.dto;


import lombok.*;

import javax.persistence.Entity;

@Data
@ToString
public class PatientRiderDto
{
    private String firstname;
    private String address;
    private String city;
    private String postal_code;
    private String phone_number;

    PatientRiderDto()
    {

    }

    public PatientRiderDto(String firstname, String address, String city, String postal_code, String phone_number) {
        this.firstname = firstname;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
    }
}
