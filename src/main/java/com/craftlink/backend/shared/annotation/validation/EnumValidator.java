package com.craftlink.backend.shared.annotation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = EnumValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EnumValidator {

  Class<? extends Enum<?>> enumClass();

  String message() default "Invalid enum value";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
