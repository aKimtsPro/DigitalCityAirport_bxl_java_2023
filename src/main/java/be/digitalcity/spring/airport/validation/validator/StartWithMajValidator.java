package be.digitalcity.spring.airport.validation.validator;

import be.digitalcity.spring.airport.validation.constraint.StartsWithMaj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartWithMajValidator implements ConstraintValidator<StartsWithMaj, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("^[A-Z].+");
    }


}
