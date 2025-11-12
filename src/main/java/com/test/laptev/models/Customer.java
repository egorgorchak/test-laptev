package com.test.laptev.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope("prototype")
public class Customer {

    private String referenceId;
    private String email;
    private String phone;
}
