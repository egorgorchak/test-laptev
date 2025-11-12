package com.test.laptev;

import com.test.laptev.models.PaymentResponse;
import com.test.laptev.service.PaymentApiClient;
import com.test.laptev.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PaymentApiClient paymentApiClient;

    @Test
    void getPaymentPage_returnsLandingView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("payment_landing"));
    }

    @Test
    void postPayment_successRedirects() throws Exception {
        PaymentResponse paymentResponse = TestUtils.readFromFile("/payment_response_success.json");

        when(paymentApiClient.createPayment(any(BigDecimal.class))).thenReturn(paymentResponse);

        mockMvc.perform(post("/payment").param("amount", "10.00"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://example.com/success"));
    }
}
