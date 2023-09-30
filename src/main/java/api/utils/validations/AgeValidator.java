package api.utils.validations;

import api.utils.validations.annotations.ValidAge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Service
@Slf4j
public class AgeValidator implements ConstraintValidator<ValidAge, Date> {

    @Value("${api-properties.min-age}")
    private int minAge;

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {

        LocalDate birthDay = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDay = LocalDate.now();

        if (date.after(new Date())) {
            return false;
        }

        return Period.between(birthDay, currentDay).getYears() >= minAge;
    }

}




