package com.craftlink.backend.config.exceptions;

import com.craftlink.backend.config.exceptions.custom.BusinessException;
import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.custom.ValidationException;
import com.craftlink.backend.config.exceptions.dtos.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponseDto> handleSecurityException(
        SecurityException ex, HttpServletRequest request) {

        logSecurityError(ex, request);

        var response = ErrorResponseDto.builder()
            .error(ex.getCode().getUserCode())
            .message(ex.getCode().getUserMessage())
            .timestamp(LocalDateTime.now())
            .build();

        return ResponseEntity.status(ex.getCode().getHttpStatus()).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusinessException(
        BusinessException ex, HttpServletRequest request) {

        if (ex.getCode().isSafeForUser()) {
            log.info("Business operation failed - Code: {}, Path: {}, Details: {}",
                ex.getCode().getCode(), request.getRequestURI(), ex.getDetails());

            var response = ErrorResponseDto.builder()
                .error(ex.getCode().getUserCode())
                .message(ex.getCode().getUserMessage())
                .timestamp(LocalDateTime.now())
                .details(convertToStringMap(ex.getDetails()))
                .build();

            return ResponseEntity.status(ex.getCode().getHttpStatus()).body(response);
        } else {
            log.error("Internal business error - Code: {}, Path: {}, Details: {}",
                ex.getCode().getCode(), request.getRequestURI(), ex.getDetails());

            var response = ErrorResponseDto.builder()
                .error(ex.getCode().getUserCode())
                .message(ex.getCode().getUserMessage())
                .timestamp(LocalDateTime.now())
                .build();

            return ResponseEntity.status(ex.getCode().getHttpStatus()).body(response);
        }
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(
        ValidationException ex, HttpServletRequest request) {

        log.warn("Validation error - Code: {}, Path: {}, Fields: {}",
            ex.getCode().getCode(), request.getRequestURI(), ex.getFieldErrors().keySet());

        var response = ErrorResponseDto.builder()
            .error(ex.getCode().getUserCode())
            .message(ex.getCode().getUserMessage())
            .timestamp(LocalDateTime.now())
            .details(ex.getFieldErrors())
            .build();

        return ResponseEntity.status(ex.getCode().getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            fieldErrors.put(error.getField(), error.getDefaultMessage()));

        log.warn("Spring validation error - Path: {}, Fields: {}",
            request.getRequestURI(), fieldErrors.keySet());

        var response = ErrorResponseDto.builder()
            .error("VALIDATION_ERROR")
            .message("Invalid request data")
            .timestamp(LocalDateTime.now())
            .details(fieldErrors)
            .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentials(
        BadCredentialsException ex, HttpServletRequest request) {

        log.warn("Bad credentials error - Path: {}, Fields: {}",
            request.getRequestURI(), ex.getMessage());

        var response = ErrorResponseDto.builder()
            .error("BAD_CREDENTIALS")
            .message("Invalid email or password")
            .timestamp(LocalDateTime.now())
            .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
        Exception ex, HttpServletRequest request) {

        log.error("Unhandled exception - Path: {}, Error: {}",
            request.getRequestURI(), ex.getMessage(), ex);

        var response = ErrorResponseDto.builder()
            .error("INTERNAL_SERVER_ERROR")
            .message("An unexpected error occurred")
            .timestamp(LocalDateTime.now())
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    private void logSecurityError(SecurityException ex, HttpServletRequest request) {
        String logMessage = String.format(
            "Security error - Code: %s, Path: %s, User: %s, UserAgent: %s",
            ex.getCode().getCode(), request.getRequestURI(),
            getCurrentUsername(), request.getHeader("User-Agent")
        );

        if (ex.getInternalDetails() != null) {
            logMessage += ", Details: " + ex.getInternalDetails();
        }

        if (!ex.getContext().isEmpty()) {
            logMessage += ", Context: " + ex.getContext();
        }

        log.error(logMessage);
    }

    private String getCurrentUsername() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : "anonymous";
    }

    private Map<String, String> convertToStringMap(Map<String, String> source) {
        return source.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> String.valueOf(entry.getValue())
            ));
    }

}
