package be.digitalcity.spring.airport.pl.models.dto;

import be.digitalcity.spring.airport.domain.FidelityStatus;
import be.digitalcity.spring.airport.domain.entity.Passenger;
import be.digitalcity.spring.airport.domain.entity.Reservation;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDTO {

    private long id;
    private double price;
    private boolean cancelled;
    private LocalDateTime createdAt;
    private FlightDTO flight;
    private PassengerDTO passenger;

    public static ReservationDTO toDTO(Reservation reservation){
        if( reservation == null )
            return null;

        return ReservationDTO.builder()
                .id(reservation.getId() )
                .price(reservation.getPrice())
                .cancelled(reservation.isCancelled())
                .createdAt(reservation.getCreatedAd())
                .flight( FlightDTO.toDTO( reservation.getReservedFlight() ))
                .passenger( PassengerDTO.toDTO(reservation.getPassenger()) )
                .build();
    }

}
