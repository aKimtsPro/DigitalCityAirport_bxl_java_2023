package be.digitalcity.spring.airport.presentation.models.dto;

import be.digitalcity.spring.airport.models.entity.Airplane;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class AirplaneDTO {

    private Long id;
    private String serialNumber;
    private LocalDate constructionDate;
    private List<FlightDTO> flights;

    public static AirplaneDTO toDTO(Airplane airplane){
        if( airplane == null )
            return null;

        return AirplaneDTO.builder()
                .id(airplane.getId())
                .serialNumber(airplane.getSerialNumber())
                .constructionDate(airplane.getConstructionDate())
                .flights(
                        airplane.getFlights().stream()
                                .map(FlightDTO::toDTO)
                                .toList()
                )
                .build();
    }

}
