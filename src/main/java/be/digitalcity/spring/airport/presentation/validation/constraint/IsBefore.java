package be.digitalcity.spring.airport.presentation.validation.constraint;

import be.digitalcity.spring.airport.presentation.validation.validator.IsBeforeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IsBeforeValidator.class})
public @interface IsBefore {

    String message() default "a date should be before the other";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String beforeField();
    String afterField();
}
