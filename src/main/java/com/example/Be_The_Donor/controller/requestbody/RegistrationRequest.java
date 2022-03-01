package com.example.Be_The_Donor.controller.requestbody;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    //We need to capture these things
    public String firstName;
    public String lastName;
    public String email;
    public String phone_number;
    public String type_of_user;
    public String password;
    public String confirm_password;

}
