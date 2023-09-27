package api.TestAPI.utils.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Service
public class AgeValidator implements ConstraintValidator<ValidAge, Date> {

    @Value("${api-properties.min-age}")
    private static int minAge;

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {

        LocalDate birthDay = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDay = LocalDate.now();

        if (date.after(new Date())) {
            return false;
        }

        return Period.between(currentDay, birthDay).getYears() >= 18;
    }
}
