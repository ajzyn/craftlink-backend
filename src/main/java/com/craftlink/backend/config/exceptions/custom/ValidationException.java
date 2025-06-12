package com.craftlink.backend.config.exceptions.custom;

import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import java.util.Map;
import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private final ExceptionCode code;
    private final Map<String, String> fieldErrors;

    public ValidationException(ExceptionCode code, Map<String, String> fieldErrors) {
        super(code.getUserMessage());
        this.fieldErrors = fieldErrors;
        this.code = code;
    }
}
