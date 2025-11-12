package com.test.laptev.errorhandling;

public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message);
    }
}
