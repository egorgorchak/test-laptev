package com.test.laptev.models;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class PaymentResponse {

    private OffsetDateTime timestamp;
    private int status;
    private PaymentResult result;
}
