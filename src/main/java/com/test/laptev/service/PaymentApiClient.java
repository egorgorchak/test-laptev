package com.test.laptev.service;

import com.test.laptev.enums.Currency;
import com.test.laptev.enums.PaymentType;
import com.test.laptev.models.Customer;
import com.test.laptev.models.PaymentFactory;
import com.test.laptev.models.PaymentRequest;
import com.test.laptev.models.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Slf4j
@Service
public class PaymentApiClient {

    @Value("${payment.api.url}")
    private String apiUrl;

    @Value("${payment.api.token}")
    private String apiToken;

    private final WebClient webClient;
    private final PaymentFactory factory;

    @Autowired
    public PaymentApiClient(WebClient webClient, PaymentFactory factory) {
        this.webClient = webClient;
        this.factory = factory;
    }

    public PaymentResponse createPayment(BigDecimal amount) {
        Customer customer = factory.createCustomer("refId", "email@m.com", "46 546"); // customer data could be fetched from somewhere...
        PaymentRequest paymentRequest = factory.createPaymentRequest(PaymentType.DEPOSIT, amount, Currency.EUR, customer);

        log.debug("Sending request to {} ...", apiUrl);
        return webClient.post()
                .uri(apiUrl)
                .headers(h -> h.setBearerAuth(apiToken))
                .bodyValue(paymentRequest)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block();
    }
}
