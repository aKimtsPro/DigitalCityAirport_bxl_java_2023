package be.digitalcity.spring.airport.models.form;

import be.digitalcity.spring.airport.models.entity.Flight;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightCreateForm {

    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price;

    private long airplaneId;
    private long pilotId;
    private long originId;
    private long destinationId;

    public Flight toEntity(){
        Flight flight = new Flight();

        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setPrice(price);

        return flight;
    }

}
