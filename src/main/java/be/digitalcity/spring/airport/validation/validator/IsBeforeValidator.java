package be.digitalcity.spring.airport.validation.validator;

import be.digitalcity.spring.airport.validation.constraint.IsBefore;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class IsBeforeValidator implements ConstraintValidator<IsBefore, Object> {

    private IsBefore annotation;

    @Override
    public void initialize(IsBefore constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        try {
            Field beforeField = value.getClass().getDeclaredField( annotation.beforeField() );
            Field afterField = value.getClass().getDeclaredField( annotation.afterField() );

            beforeField.setAccessible(true);
            LocalDateTime beforeDate = (LocalDateTime) beforeField.get(value);
            beforeField.setAccessible(false);

            afterField.setAccessible(true);
            LocalDateTime afterDate = (LocalDateTime) afterField.get(value);
            afterField.setAccessible(false);

            boolean isValid = beforeDate.isBefore(afterDate);
            if( !isValid ){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        "%s should be before %s".formatted(
                                annotation.beforeField(),
                                annotation.afterField()
                        )
                ).addConstraintViolation();
                return false;
            }
            return true;

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
