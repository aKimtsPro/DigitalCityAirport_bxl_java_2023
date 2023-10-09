package be.digitalcity.spring.airport.bl.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FlightDepartureArrivalException extends RuntimeException {

    private final LocalDateTime departure;
    private final LocalDateTime arrival;

    public FlightDepartureArrivalException(LocalDateTime departure, LocalDateTime arrival) {
        super("arrival {%s} should be after departure {%s}".formatted(arrival.toString(), departure.toString()));
        this.departure = departure;
        this.arrival = arrival;
    }
}
