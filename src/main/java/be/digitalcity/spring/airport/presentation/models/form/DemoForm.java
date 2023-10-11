package be.digitalcity.spring.airport.presentation.models.form;

import be.digitalcity.spring.airport.presentation.validation.constraint.Even;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DemoForm {

    @Positive
//    @PositiveOrZero
//    @Negative
//    @NegativeOrZero
    @Min(0)
    @Max(10)
    @Even
    private long number;
    private double numberDouble;

//    @AssertFalse
    @AssertTrue
    private Boolean aBoolean;

    @NotNull
//    @Null
    private Object object;

//    @Past
//    @PastOrPresent
//    @Future
    @FutureOrPresent
    private LocalDate date;
    private LocalDateTime datetime;

    @NotEmpty // doit contenir au min 1 element
    @Size(min = 1, max = 25)
    private List<Object> collections;

    @Size(min = 5, max = 20)
    @NotBlank
//    @Email
    @Pattern(regexp = "^a[0-9]+b$")
    private String string;


}
