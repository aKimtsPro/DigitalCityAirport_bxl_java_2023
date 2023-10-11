package be.digitalcity.spring.airport.pl.models.form;

import lombok.Data;

@Data
public class ReservationForm {

    private long passengerId;
    private long flightId;

}
