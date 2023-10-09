package be.digitalcity.spring.airport.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class DateTooSoonException extends RuntimeException {

    private final String fieldName;
    private final LocalDateTime date;
    private final LocalDateTime minDate;

    public DateTooSoonException(LocalDateTime date, LocalDateTime minDate, String fieldName) {
        super("%s {%s} should be at minimum {%s}".formatted(fieldName, date.toString(), minDate.toString()));
        this.fieldName = fieldName;
        this.date = date;
        this.minDate = minDate;
    }
}
