package com.example.Be_The_Donor.controller.requestbody;


import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    public RegistrationRequest()
    {

    }

    //We need to capture these things

    @NotEmpty(message = "user first name can not be empty")
    @Size(min = 3,max = 70)
    public String firstName;
    @NotEmpty(message = "user last name can not be empty")
    @Size(min = 3,max = 70)
    public String lastName;
    @NotEmpty(message = "user email can not be empty")
    @Email(message = "Please Provide a valid Gmail")
    @Pattern(regexp = "^[a-zA-Z][-_.a-zA-Z0-9]{5,29}@g(oogle)?mail.com$",message = " Please enter valid gmail (google mail address)")
    public String email;
    @NotNull(message = "Please fill the phone number field")
    @Size(min = 9,max = 10)
    public String phone_number;
    public String type_of_user;
    @NotEmpty(message = "Password can not be empty")
    public String password;
    @NotEmpty(message = "Confirm Password can not be empty")
    public String confirm_password;

}
