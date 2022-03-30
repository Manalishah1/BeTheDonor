package com.beTheDonor.service;

import org.springframework.stereotype.Service;

@Service
public interface CreditAmountService {
    void orderFromCredits();

    void updateTipAmount(Double tip);
}
