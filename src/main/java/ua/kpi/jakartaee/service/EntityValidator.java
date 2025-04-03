package ua.kpi.jakartaee.service;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.*;

import java.util.List;
import java.util.Set;

//@ApplicationScoped
@Stateless
public class EntityValidator {

    private final Validator validator;

    public EntityValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    public <T> void validate(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            List<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();
            throw new ValidationException(errorMessages.toString());
        }
    }
}
