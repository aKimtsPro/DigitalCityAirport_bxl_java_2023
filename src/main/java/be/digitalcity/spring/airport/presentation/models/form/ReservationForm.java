package be.digitalcity.spring.airport.presentation.models.form;

import lombok.Data;

@Data
public class ReservationForm {

    private long passengerId;
    private long flightId;

}
