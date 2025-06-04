package com.craftlink.backend.config.exceptions.custom;

import com.craftlink.backend.config.exceptions.ApplicationException;
import com.craftlink.backend.config.exceptions.ExceptionCode;

public class UserAlreadyExistException extends ApplicationException {

    public UserAlreadyExistException() {
        super(ExceptionCode.USER_ALREADY_EXIST);
    }
}
