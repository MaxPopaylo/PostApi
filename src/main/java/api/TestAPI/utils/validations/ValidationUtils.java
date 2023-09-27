package api.TestAPI.utils.validations;

import api.TestAPI.utils.validations.AgeValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationUtils {

    private final Validator validator;
    private final AgeValidator userValidator;

    public <T> void validationRequest(T req) {

        if (req != null) {
            Set<ConstraintViolation<T>> result = validator.validate(req);
            if (!result.isEmpty()) {
                String validationRequest = result.stream()
                        .map(violation -> "\"" + violation.getMessage() + "\"")
                        .collect(Collectors.joining(", "));
                log.error("Validation Exceptions: ", validationRequest);

                throw new ValidationException(validationRequest);
            }
        }
    }

}
