package com.test.laptev.errorhandling;

import com.test.laptev.errorhandling.errors.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_MSG_PATTERN = "Invalid Request with status %d: field: %s, error: %s";

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        log.error("Unknown error - {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(WebClientResponseException.class)
    public String handleWebClientException(WebClientResponseException ex, Model model) {
        ErrorResponse errorResponse = ex.getResponseBodyAs(ErrorResponse.class);
        log.error("Error response from the client: {}, status = {}", errorResponse.getError(), errorResponse.getStatus());

        List<String> errMsg = getErrors(errorResponse);
        model.addAttribute("errorMessage", errMsg);
        return "error";
    }

    private List<String> getErrors(ErrorResponse responseBody) {
        List<String> errMsg = new ArrayList<>();
        if (responseBody != null && responseBody.getErrors() != null) {
            errMsg = responseBody.getErrors().stream()
                    .map(err -> String.format(ERROR_MSG_PATTERN, responseBody.getStatus(), err.getField(), err.getDefaultMessage()))
                    .toList();
        } else if (responseBody != null) {
            errMsg.add(responseBody.getError());
        }
        return errMsg;
    }


}
