package com.craftlink.backend.config.exceptions;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionCode {
    JWT_SECRET_TOO_SHORT("JWT_SECRET_TOO_SHORT", "JWT Generation error - secret too short"),
    JWT_EXP_TIME_NULL("JWT_EXP_TIME_NULL", "JWT Generation error - no expiration time provided"),
    JWT_GENERAL_EXCEPTION("JWT_GENERAL_EXCEPTION", "JWT Generation error"),
    JWT_EXPIRED("JWT_EXPIRED", "Token has expired"),
    JWT_NOT_VALID("JWT_NOT_VALID", "JWT is not valid"),
    USER_NOT_EXIST("USER_NOT_EXIST", "User does not exist"),
    USER_ALREADY_EXIST("USER_ALREADY_EXIST", "User already exit"),
    SPECIALIST_NOT_REGISTERED("SPECIALIST_NOT_REGISTERED", "Specialist is not registered"),
    CLIENT_NOT_REGISTERED("CLIENT_NOT_REGISTERED", "Client is not registered");

    private final String name;
    private final String message;
}
