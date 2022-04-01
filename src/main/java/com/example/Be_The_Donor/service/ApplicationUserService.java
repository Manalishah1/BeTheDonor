package com.example.Be_The_Donor.service;


import com.example.Be_The_Donor.entity.ApplicationUser;
import com.example.Be_The_Donor.entity.UserConfirmationToken;
import com.example.Be_The_Donor.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ApplicationUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = " User with email %s not found ";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }

    public String signUpUser(ApplicationUser applicationUser)
    {
        boolean userExist = userRepository.findByEmail(applicationUser.getEmail()).isPresent();
        if(userExist)
        {

            throw new IllegalStateException("Email already exist");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(encodedPassword);

        userRepository.save(applicationUser);

        String token =  UUID.randomUUID().toString();

        UserConfirmationToken userConfirmationToken = new UserConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                applicationUser
        );

        confirmationTokenService.saveConfirmationToken(userConfirmationToken);

        return token ;
    }

    public void enableApplicationUser(String email) {
        userRepository.enableApplicationUser(email);

    }

    public List<ApplicationUser> getPatients(){ return userRepository.getPatient();}

    public List<ApplicationUser> getRider(){ return userRepository.getRider();}

    public List<ApplicationUser> getDonor(){ return userRepository.getDonor();}

    public List<ApplicationUser> findAll(){return userRepository.findAll();}

    public Optional<ApplicationUser> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
