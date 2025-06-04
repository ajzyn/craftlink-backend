package com.craftlink.backend.config.exceptions.custom;

import com.craftlink.backend.config.exceptions.ApplicationException;
import com.craftlink.backend.config.exceptions.ExceptionCode;

public class JwtGenerationException extends ApplicationException {

    public JwtGenerationException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }

    public JwtGenerationException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
