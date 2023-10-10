package be.digitalcity.spring.airport.validation.validator;

import be.digitalcity.spring.airport.validation.constraint.StartsWithMaj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartWithMajValidator implements ConstraintValidator<StartsWithMaj, String> {

    private int numberCapital;

    @Override
    public void initialize(StartsWithMaj constraintAnnotation) {
        numberCapital = constraintAnnotation.numberCapital();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = "^[A-Z]{%d}.+".formatted(numberCapital);
        return value.matches(regex);
    }


}
