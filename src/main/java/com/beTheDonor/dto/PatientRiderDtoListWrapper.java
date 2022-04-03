package com.beTheDonor.dto;

import java.util.ArrayList;


public class PatientRiderDtoListWrapper {

   public ArrayList<PatientRiderDto> patientRiderDtos ;

   public void setPatientRiderDto(ArrayList<PatientRiderDto> patientRiderDtos) {
      this.patientRiderDtos = patientRiderDtos;
   }

   public ArrayList<PatientRiderDto> getPatientRiderDto()
   {
      return patientRiderDtos;
   }

}
