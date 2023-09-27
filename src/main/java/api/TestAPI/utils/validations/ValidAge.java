package api.TestAPI.utils.validations;

import api.TestAPI.utils.validations.AgeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Target({TYPE, FIELD, PARAMETER, RECORD_COMPONENT})
@Constraint(validatedBy = AgeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAge {
    String message() default "Invalid age or you are younger than 18 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
