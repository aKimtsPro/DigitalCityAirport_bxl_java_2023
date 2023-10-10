package be.digitalcity.spring.airport.models.form;

import be.digitalcity.spring.airport.models.entity.Flight;
import be.digitalcity.spring.airport.validation.constraint.IsBefore;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@IsBefore(beforeField = "departure", afterField = "arrival")
public class FlightCreateForm {

    @Future
    @NotNull
    private LocalDateTime departure;
    @Future
    @NotNull
    private LocalDateTime arrival;
    @Positive
    private double price = 10;

    @NotNull
    private Long airplaneId;
    @NotNull
    private Long pilotId;
    @NotNull
    private Long originId;
    @NotNull
    private Long destinationId;

    public Flight toEntity(){
        Flight flight = new Flight();

        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setPrice(price);

        return flight;
    }

}
