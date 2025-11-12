package com.test.laptev.errorhandling.errors;

import lombok.Data;

import java.util.List;

@Data
public class FieldError {

    private List<String> codes;
    private List<Object> arguments;
    private String defaultMessage;
    private String objectName;
    private String field;
    private Object rejectedValue;
    private boolean bindingFailure;
}
