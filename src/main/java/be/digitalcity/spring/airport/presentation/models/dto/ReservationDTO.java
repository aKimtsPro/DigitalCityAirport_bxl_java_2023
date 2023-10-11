package be.digitalcity.spring.airport.presentation.models.dto;

import be.digitalcity.spring.airport.models.FidelityStatus;
import be.digitalcity.spring.airport.models.entity.Passenger;
import be.digitalcity.spring.airport.models.entity.Reservation;
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

    @Data
    @Builder
    public static class PassengerDTO {
        private long id;
        private String firstname;
        private String lastname;
        private FidelityStatus status;

        public static PassengerDTO toDTO(Passenger passenger){
            if(passenger == null)
                return null;

            return PassengerDTO.builder()
                    .id(passenger.getId())
                    .firstname(passenger.getFirstname())
                    .lastname(passenger.getLastname())
                    .status(passenger.getStatus())
                    .build();
        }
    }
}
