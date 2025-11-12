package com.test.laptev.service;

import com.test.laptev.models.PaymentResponse;
import com.test.laptev.models.PaymentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final PaymentApiClient paymentApiClient;
    private final ValidationService validationService;

    @Autowired
    public PaymentService(PaymentApiClient paymentApiClient, ValidationService validationService) {
        this.paymentApiClient = paymentApiClient;
        this.validationService = validationService;
    }

    public PaymentResult processPayment(BigDecimal amount) {
        PaymentResponse paymentResponse = paymentApiClient.createPayment(amount);
        validationService.validateResponse(paymentResponse);

        return paymentResponse.getResult();
    }

    public String getRedirectUrl(PaymentResult paymentResult) {
        String redirectUrl = paymentResult.getRedirectUrl();
        validationService.validateUrl(redirectUrl);

        return redirectUrl;
    }
}
