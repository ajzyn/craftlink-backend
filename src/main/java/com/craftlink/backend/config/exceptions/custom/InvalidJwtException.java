package com.craftlink.backend.config.exceptions.custom;

import com.craftlink.backend.config.exceptions.ApplicationException;
import com.craftlink.backend.config.exceptions.ExceptionCode;

public class InvalidJwtException extends ApplicationException {

    public InvalidJwtException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidJwtException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }
}
