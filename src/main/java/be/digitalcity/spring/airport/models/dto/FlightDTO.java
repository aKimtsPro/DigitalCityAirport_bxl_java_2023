package be.digitalcity.spring.airport.models.dto;

import be.digitalcity.spring.airport.models.entity.Airplane;
import be.digitalcity.spring.airport.models.entity.Airport;
import be.digitalcity.spring.airport.models.entity.Flight;
import be.digitalcity.spring.airport.models.entity.Pilot;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class FlightDTO {

    private Long id;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private double price;
    private boolean cancelled;

    private AirplaneDTO airplane;
    private PilotDTO pilot;
    private AirportDTO origin;
    private AirportDTO destination;

    public static FlightDTO toDTO(Flight flight){
        if( flight == null )
            return null;

        return FlightDTO.builder()
                .id(flight.getId())
                .departure(flight.getDeparture())
                .arrival(flight.getArrival())
                .price(flight.getPrice())
                .cancelled(flight.isCancelled())
                .airplane( AirplaneDTO.toDTO( flight.getAirplane() ) )
                .pilot( PilotDTO.toDTO( flight.getPilot() ) )
                .origin( AirportDTO.toDTO( flight.getOrigin() ) )
                .destination( AirportDTO.toDTO( flight.getDestination() ) )
                .build();
    }

    @Data
    @Builder
    private static class AirplaneDTO {
        private Long id;
        private String serialNumber;

        public static AirplaneDTO toDTO(Airplane airplane){
            if( airplane == null )
                return null;

            return AirplaneDTO.builder()
                    .id( airplane.getId() )
                    .serialNumber( airplane.getSerialNumber() )
                    .build();
        }
    }

    @Data
    @Builder
    private static class PilotDTO {
        private Long id;
        private String firstname;
        private String lastname;

        private static PilotDTO toDTO(Pilot pilot){
            if( pilot == null )
                return null;

            return PilotDTO.builder()
                    .id(pilot.getId())
                    .firstname(pilot.getFirstname())
                    .lastname(pilot.getLastname())
                    .build();
        }
    }

    @Data
    @Builder
    private static class AirportDTO {

        private Long id;
        private String nom;
        private String address;

        private static AirportDTO toDTO(Airport airport){
            if(airport == null)
                return null;

            return AirportDTO.builder()
                    .id(airport.getId())
                    .nom(airport.getName())
                    .address(airport.getAddress())
                    .build();
        }

    }

}
