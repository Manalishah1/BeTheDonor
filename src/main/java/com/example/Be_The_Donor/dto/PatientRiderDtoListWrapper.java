package com.example.Be_The_Donor.dto;

import java.util.ArrayList;


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
