package com.craftlink.backend.config.exceptions.custom;

import com.craftlink.backend.config.exceptions.ApplicationException;
import com.craftlink.backend.config.exceptions.ExceptionCode;

public class InvalidCredentialsException extends ApplicationException {

    public InvalidCredentialsException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public InvalidCredentialsException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }
}
