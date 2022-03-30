package com.example.Be_The_Donor.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


public class PatientRiderDtoListWrapper {

   public ArrayList<PatientRiderDto> patientRiderDtos ;


   public PatientRiderDtoListWrapper()
   {

   }

   public void setPatientRiderDtos(ArrayList<PatientRiderDto> patientRiderDtos) {
      this.patientRiderDtos = patientRiderDtos;
   }

   public ArrayList<PatientRiderDto> getPatientRiderDto()
   {
      return patientRiderDtos;
   }


}
