package org.labs.lab4;

import jakarta.validation.Validation;

public class Validator {
  private static final jakarta.validation.Validator validator = Validation.buildDefaultValidatorFactory()
      .getValidator();

  public static <T> T validateAndReturn(T object) throws BadValidationException {
    var constraints = validator.validate(object);
    if (constraints.size() > 0) {
      throw new BadValidationException("Bad validation object");
    }
    return object;
  }
}
