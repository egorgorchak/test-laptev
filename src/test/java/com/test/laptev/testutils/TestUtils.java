package com.test.laptev.testutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.test.laptev.models.PaymentResponse;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

public final class TestUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    private TestUtils() {
    }

    public static PaymentResponse readFromFile(String resourcePath) throws IOException {
        ClassPathResource r = new ClassPathResource(resourcePath);
        try (InputStream in = r.getInputStream()) {
            return MAPPER.readValue(in, PaymentResponse.class);
        }
    }
}

