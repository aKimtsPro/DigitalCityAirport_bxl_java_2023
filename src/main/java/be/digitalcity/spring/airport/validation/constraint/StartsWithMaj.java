package be.digitalcity.spring.airport.validation.constraint;

import be.digitalcity.spring.airport.validation.validator.StartWithMajValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = StartWithMajValidator.class)
public @interface StartsWithMaj {

    String message() default "must start with (one) capital(s)";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int numberCapital() default 1;

}
