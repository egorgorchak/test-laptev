package com.test.laptev.service;

import com.test.laptev.errorhandling.PaymentException;
import com.test.laptev.models.PaymentResponse;
import com.test.laptev.models.PaymentResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Service
public class ValidationService {

    public void validateResponse(PaymentResponse paymentResponse) {
        // request statuses are handled by GlobalExceptionHandler

        if (paymentResponse == null) {
            log.error("Returned PaymentResponse is null");
            throw new PaymentException("PaymentResponse is null");
        }

        PaymentResult result = paymentResponse.getResult();
        if (result == null) {
            log.error("result field of PaymentResponse is null");
            throw new PaymentException("Result of Payment Response is null");
        }
    }

    public void validateUrl(String url) {
        try {
            new URL(url);
        } catch (MalformedURLException e) {
            log.error("Given link is invalid - {}", url);
            throw new PaymentException("RedirectUrl is malformed");
        }
    }
}
