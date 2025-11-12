package com.test.laptev.controller;

import com.test.laptev.models.PaymentResult;
import com.test.laptev.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public String getPaymentPage() {
        return "payment_landing";
    }

    @PostMapping("/payment")
    @ResponseBody
    public RedirectView processPayment(@RequestParam("amount") BigDecimal amount) {
        PaymentResult paymentResult = paymentService.processPayment(amount);
        String redirectUrl = paymentService.getRedirectUrl(paymentResult);

        return new RedirectView(redirectUrl);
    }
}
