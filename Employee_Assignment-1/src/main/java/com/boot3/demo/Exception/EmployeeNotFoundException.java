package com.boot3.demo.Exception;

import java.io.Serializable;

public class EmployeeNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
