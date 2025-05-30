package ua.kpi.jakartaee.service;

import jakarta.ejb.Stateless;
import jakarta.validation.*;
import ua.kpi.jakartaee.exceptions.ValidationException;

import java.util.List;
import java.util.Set;

@Stateless
public class EntityValidator {

    private final Validator validator;

    public EntityValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    public <T> void validate(T entity) throws ValidationException {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();
            throw new ValidationException(errorMessages.toString());
        }
    }
}
