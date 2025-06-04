package com.craftlink.backend.config.exceptions.custom;

import com.craftlink.backend.config.exceptions.ApplicationException;
import com.craftlink.backend.config.exceptions.ExceptionCode;

public class UserPersistenceException extends ApplicationException {

    public UserPersistenceException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public UserPersistenceException(ExceptionCode exceptionCode, Throwable ex) {
        super(exceptionCode, ex);
    }
}
