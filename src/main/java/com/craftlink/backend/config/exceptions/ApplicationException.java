package com.craftlink.backend.config.exceptions;

import lombok.Getter;

@Getter
public abstract class ApplicationException extends RuntimeException{
    
    private final ExceptionCode exceptionCode;

    protected ApplicationException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }

    protected ApplicationException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode.getMessage(), ex);
        this.exceptionCode = exceptionCode;
    }
}
