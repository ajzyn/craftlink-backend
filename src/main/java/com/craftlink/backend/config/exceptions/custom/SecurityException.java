package com.craftlink.backend.config.exceptions.custom;

import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import java.util.Map;
import lombok.Getter;

@Getter
public class SecurityException extends RuntimeException {

    private final ExceptionCode code;
    private final String internalDetails;
    private final Map<String, Object> context;

    public SecurityException(ExceptionCode code) {
        this(code, null, Map.of());
    }

    public SecurityException(ExceptionCode code, String internalDetails) {
        this(code, internalDetails, Map.of());

    }

    public SecurityException(ExceptionCode code, String internalDetails, Map<String, Object> context) {
        super(code.getUserMessage());
        this.code = code;
        this.internalDetails = internalDetails;
        this.context = context;
    }

    public SecurityException(ExceptionCode code, Throwable cause) {
        this(code, null, cause, Map.of());
    }

    public SecurityException(ExceptionCode code, String internalDetails, Throwable cause) {
        this(code, internalDetails, cause, Map.of());
    }

    public SecurityException(ExceptionCode code, String internalDetails, Throwable cause, Map<String, Object> ctx) {
        super(code.getUserMessage(), cause);
        this.code = code;
        this.internalDetails = internalDetails;
        this.context = ctx;
    }
}
