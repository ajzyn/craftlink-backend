package com.craftlink.backend.shared.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, Object> {

  private Class<? extends Enum<?>> enumClass;

  @Override
  public void initialize(EnumValidator constraintAnnotation) {
    this.enumClass = constraintAnnotation.enumClass();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return false;
    }

    for (Enum<?> enumValue : enumClass.getEnumConstants()) {
      if (enumValue.name().equals(value.toString())) {
        return true;
      }
    }

    return false;
  }
}
