package be.digitalcity.spring.airport.models.dto;

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

}
