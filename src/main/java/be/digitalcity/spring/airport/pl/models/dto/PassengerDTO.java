package be.digitalcity.spring.airport.pl.models.dto;

import be.digitalcity.spring.airport.domain.FidelityStatus;
import be.digitalcity.spring.airport.domain.entity.Passenger;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerDTO {
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
