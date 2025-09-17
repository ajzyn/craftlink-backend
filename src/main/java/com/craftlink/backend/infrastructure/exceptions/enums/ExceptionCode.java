package com.craftlink.backend.infrastructure.exceptions.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionCode {
  UNAUTHORIZED("UNAUTHORIZED", "Access denied", HttpStatus.UNAUTHORIZED, true),
  FORBIDDEN("FORBIDDEN", "Insufficient permissions", HttpStatus.FORBIDDEN, true),
  AUTHENTICATION_FAILED("AUTHENTICATION_FAILED", "Invalid credentials", HttpStatus.UNAUTHORIZED, true),
  SESSION_EXPIRED("SESSION_EXPIRED", "Your session has expired", HttpStatus.UNAUTHORIZED, true),

  JWT_EXPIRED("JWT_EXPIRED", "Token has expired", HttpStatus.UNAUTHORIZED, true),
  JWT_INVALID("JWT_INVALID", "Invalid authentication token", HttpStatus.UNAUTHORIZED, true),
  TOKEN_FORMAT_ERROR("TOKEN_FORMAT_ERROR", "Authentication error", HttpStatus.UNAUTHORIZED, false),

  REFRESH_TOKEN_EXPIRED("REFRESH_TOKEN_EXPIRED", "Refresh token has expired", HttpStatus.UNAUTHORIZED, true),
  REFRESH_TOKEN_INVALID("REFRESH_TOKEN_INVALID", "Invalid refresh token", HttpStatus.UNAUTHORIZED, true),

  VALIDATION_ERROR("VALIDATION_ERROR", "Invalid request data", HttpStatus.BAD_REQUEST, true),
  MISSING_REQUIRED_FIELD("MISSING_REQUIRED_FIELD", "Required field is missing", HttpStatus.BAD_REQUEST, true),
  INVALID_FORMAT("INVALID_FORMAT", "Invalid data format", HttpStatus.BAD_REQUEST, true),

  JWT_INTERNAL_ERROR("JWT_INTERNAL_ERROR", "Authentication service error", HttpStatus.INTERNAL_SERVER_ERROR, false),
  SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE", "Service temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE,
      false),
  CONFIGURATION_ERROR("CONFIGURATION_ERROR", "Server configuration error", HttpStatus.INTERNAL_SERVER_ERROR, false),

  USER_ENUMERATION_ATTEMPT("USER_ENUMERATION_ATTEMPT", "Authentication failed", HttpStatus.UNAUTHORIZED, false),


  USER_NOT_FOUND("USER_NOT_FOUND", "User not found", HttpStatus.NOT_FOUND, true),
  USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "User already exists", HttpStatus.CONFLICT, true),
  ACCOUNT_LOCKED("ACCOUNT_LOCKED", "Account is locked", HttpStatus.FORBIDDEN, true),
  ACCOUNT_NOT_ACTIVATED("ACCOUNT_NOT_ACTIVATED", "Account not activated", HttpStatus.FORBIDDEN, true),


  //business
  REGISTRATION_FAILED("REGISTRATION_FAILED", "Registration failed", HttpStatus.BAD_REQUEST, true),
  RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Resource not found", HttpStatus.NOT_FOUND, true),
  OPERATION_NOT_ALLOWED("OPERATION_NOT_ALLOWED", "Operation not allowed", HttpStatus.METHOD_NOT_ALLOWED, true),
  TOO_MANY_REQUESTS("TOO_MANY_REQUESTS", "Too many requests. Please try again later", HttpStatus.TOO_MANY_REQUESTS,
      true),

  //aws
  FAILED_GENERATE_S3_PRESIGNED_URL("FAILED_GENERATE_S3_PRESIGNED_URL",
      "Unable to generate upload link. Please try again in a moment.",
      HttpStatus.INTERNAL_SERVER_ERROR, false),
  FAILED_VERIFYING_FILE("FAILED_VERIFYING_FILE",
      "Unable to verify uploaded image on S3",
      HttpStatus.INTERNAL_SERVER_ERROR, false),
  FAILED_COMPLETE_UPLOADING("FAILED_COMPLETE_UPLOADING",
      "Unable to mark uploaded file as completed on S3",
      HttpStatus.INTERNAL_SERVER_ERROR, false),
  FAILED_TO_REMOVE_FILE("FAILED_TO_REMOVE_FILE",
      "Unable to delete image from storage",
      HttpStatus.INTERNAL_SERVER_ERROR, false),
  AWS_FILE_NOT_FOUND("AWS_FILE_NOT_FOUND",
      "Unable to find a file in AWS",
      HttpStatus.INTERNAL_SERVER_ERROR, false),


  //file validation
  FILE_IS_TOO_BIG("FILE_IS_TOO_LARGE", "File size exceeds maximum allowed limit. Please choose a smaller file.",
      HttpStatus.BAD_REQUEST, true),
  EXTENSION_NOT_ALLOWED("EXTENSION_NOT_ALLOWED",
      "File type is not supported",
      HttpStatus.BAD_REQUEST, false),
  CONTENT_TYPE_NOT_ALLOWED("CONTENT_TYPE_NOT_ALLOWED",
      "File format is not supported.",
      HttpStatus.BAD_REQUEST, false),


  //configuration
  FAILED_TO_LOAD_FILE("FAILED_TO_LOAD_FILE", "Failed to load file", HttpStatus.INTERNAL_SERVER_ERROR, false);


  private final String code;
  private final String userMessage;
  private final HttpStatus httpStatus;
  private final boolean safeForUser;

  public String getUserMessage() {
    return safeForUser ? userMessage : "An error occurred while processing your request";
  }

  public String getUserCode() {
    return safeForUser ? code : "INTERNAL_ERROR";
  }
}