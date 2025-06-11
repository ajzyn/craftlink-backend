package com.craftlink.backend.config.exceptions;

import com.craftlink.backend.config.exceptions.custom.BusinessException;
import com.craftlink.backend.config.exceptions.custom.SecurityException;
import com.craftlink.backend.config.exceptions.custom.ValidationException;
import com.craftlink.backend.config.exceptions.dtos.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        String requestId = generateRequestId();

        logSecurityError(ex, request, requestId);

        var response = ErrorResponseDto.builder()
            .error(ex.getCode().getUserCode())
            .message(ex.getCode().getUserMessage())
            .requestId(requestId)
            .timestamp(LocalDateTime.now())
            .build();

        return ResponseEntity.status(ex.getCode().getHttpStatus()).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusinessException(
        BusinessException ex, HttpServletRequest request) {

        String requestId = generateRequestId();

        if (ex.getCode().isSafeForUser()) {
            log.info("Business operation failed [{}] - Code: {}, Path: {}, Details: {}",
                requestId, ex.getCode().getCode(), request.getRequestURI(), ex.getDetails());

            var response = ErrorResponseDto.builder()
                .error(ex.getCode().getUserCode())
                .message(ex.getCode().getUserMessage())
                .requestId(requestId)
                .timestamp(LocalDateTime.now())
                .details(convertToStringMap(ex.getDetails()))
                .build();

            return ResponseEntity.status(ex.getCode().getHttpStatus()).body(response);
        } else {
            log.error("Internal business error [{}] - Code: {}, Path: {}, Details: {}",
                requestId, ex.getCode().getCode(), request.getRequestURI(), ex.getDetails());

            var response = ErrorResponseDto.builder()
                .error(ex.getCode().getUserCode())
                .message(ex.getCode().getUserMessage())
                .requestId(requestId)
                .timestamp(LocalDateTime.now())
                .build();

            return ResponseEntity.status(ex.getCode().getHttpStatus()).body(response);
        }
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(
        ValidationException ex, HttpServletRequest request) {

        String requestId = generateRequestId();

        log.warn("Validation error [{}] - Code: {}, Path: {}, Fields: {}",
            requestId, ex.getCode().getCode(), request.getRequestURI(), ex.getFieldErrors().keySet());

        var response = ErrorResponseDto.builder()
            .error(ex.getCode().getUserCode())
            .message(ex.getCode().getUserMessage())
            .requestId(requestId)
            .timestamp(LocalDateTime.now())
            .details(ex.getFieldErrors())
            .build();

        return ResponseEntity.status(ex.getCode().getHttpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpServletRequest request) {

        String requestId = generateRequestId();

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            fieldErrors.put(error.getField(), error.getDefaultMessage()));

        log.warn("Spring validation error [{}] - Path: {}, Fields: {}",
            requestId, request.getRequestURI(), fieldErrors.keySet());

        var response = ErrorResponseDto.builder()
            .error("VALIDATION_ERROR")
            .message("Invalid request data")
            .requestId(requestId)
            .timestamp(LocalDateTime.now())
            .details(fieldErrors)
            .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
        Exception ex, HttpServletRequest request) {

        String requestId = generateRequestId();

        log.error("Unhandled exception [{}] - Path: {}, Error: {}",
            requestId, request.getRequestURI(), ex.getMessage(), ex);

        var response = ErrorResponseDto.builder()
            .error("INTERNAL_SERVER_ERROR")
            .message("An unexpected error occurred")
            .requestId(requestId)
            .timestamp(LocalDateTime.now())
            .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    //TODO: sprawdzic stacktrace - czy dziala poprawnie z super(ex)

    private void logSecurityError(SecurityException ex, HttpServletRequest request, String requestId) {
        String logMessage = String.format(
            "Security error [%s] - Code: %s, Path: %s, User: %s, UserAgent: %s",
            requestId, ex.getCode().getCode(), request.getRequestURI(),
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

    private String generateRequestId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }


    private String getCurrentUsername() {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            return auth != null ? auth.getName() : "anonymous";
        } catch (Exception e) {
            return "unknown";
        }
    }

    private Map<String, String> convertToStringMap(Map<String, String> source) {
        return source.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> String.valueOf(entry.getValue())
            ));
    }

}
