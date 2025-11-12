package com.test.laptev.models;

import com.test.laptev.enums.Currency;
import com.test.laptev.enums.PaymentType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResult {

    private String id;
    private LocalDateTime created;
    private PaymentType paymentType;
    private String state;
    private BigDecimal amount;
    private Currency currency;
    private String redirectUrl;
    private Customer customer;
    private ExternalRefs externalRefs;
}
