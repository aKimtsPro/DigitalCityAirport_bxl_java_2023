package be.digitalcity.spring.airport.presentation.models.dto;

import be.digitalcity.spring.airport.models.entity.Airport;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportDTO {

    private long id;
    private String address;
    private String name;

    public static AirportDTO toDTO(Airport airport){
        if( airport == null )
            return null;

        return AirportDTO.builder()
                .id(airport.getId())
                .address(airport.getAddress())
                .name(airport.getName())
                .build();
    }

}
