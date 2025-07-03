package com.craftlink.backend.config.exceptions.custom;

import com.craftlink.backend.config.exceptions.enums.ExceptionCode;

public class ConfigurationException extends RuntimeException {

    private final ExceptionCode code;

    public ConfigurationException(ExceptionCode code, Throwable cause) {
        super(code.getUserMessage(), cause);
        this.code = code;
    }
}
