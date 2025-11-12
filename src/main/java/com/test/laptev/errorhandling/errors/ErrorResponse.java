package com.test.laptev.errorhandling.errors;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class ErrorResponse {

    private OffsetDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<FieldError> errors;
    private String path;
}
