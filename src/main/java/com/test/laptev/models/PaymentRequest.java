package com.test.laptev.models;

import com.test.laptev.enums.Currency;
import com.test.laptev.enums.PaymentType;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
@Scope("prototype")
public class PaymentRequest {

    private PaymentType paymentType;
    private BigDecimal amount;
    private Currency currency;
    private Customer customer;
}
