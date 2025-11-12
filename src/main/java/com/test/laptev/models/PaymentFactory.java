package com.test.laptev.models;

import com.test.laptev.enums.Currency;
import com.test.laptev.enums.PaymentType;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentFactory {

    private final ObjectProvider<Customer> customerProvider;
    private final ObjectProvider<PaymentRequest> paymentRequestProvider;

    @Autowired
    public PaymentFactory(ObjectProvider<Customer> customerProvider,
                          ObjectProvider<PaymentRequest> paymentRequestProvider) {
        this.customerProvider = customerProvider;
        this.paymentRequestProvider = paymentRequestProvider;
    }

    public Customer createCustomer(String referenceId, String email, String phone) {
        Customer customer = customerProvider.getObject();
        customer.setReferenceId(referenceId);
        customer.setEmail(email);
        customer.setPhone(phone);
        return customer;
    }

    public PaymentRequest createPaymentRequest(PaymentType paymentType, BigDecimal amount, Currency currency, Customer customer) {
        PaymentRequest paymentRequest = paymentRequestProvider.getObject();
        paymentRequest.setPaymentType(paymentType);
        paymentRequest.setAmount(amount);
        paymentRequest.setCurrency(currency);
        paymentRequest.setCustomer(customer);
        return paymentRequest;
    }
}
