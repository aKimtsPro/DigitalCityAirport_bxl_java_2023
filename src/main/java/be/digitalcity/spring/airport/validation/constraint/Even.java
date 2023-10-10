package be.digitalcity.spring.airport.validation.constraint;

import be.digitalcity.spring.airport.validation.validator.EvenValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EvenValidator.class) // TODO modify
public @interface Even {

    String message() default "number should be even";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
