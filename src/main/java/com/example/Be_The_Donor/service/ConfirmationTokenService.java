package com.example.Be_The_Donor.service;


import com.example.Be_The_Donor.entity.UserConfirmationToken;
import com.example.Be_The_Donor.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService
{
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(UserConfirmationToken token)
    {
        confirmationTokenRepository.save(token);
    }


    public Optional<UserConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token)
    {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

}
