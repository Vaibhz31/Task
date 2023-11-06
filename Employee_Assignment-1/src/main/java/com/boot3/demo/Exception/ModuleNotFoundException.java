package com.boot3.demo.Exception;

import java.io.Serializable;

public class ModuleNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public ModuleNotFoundException(String message) {
        super(message);
    }
}
