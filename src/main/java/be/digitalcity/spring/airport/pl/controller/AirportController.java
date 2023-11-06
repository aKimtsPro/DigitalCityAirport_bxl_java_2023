package be.digitalcity.spring.airport.pl.controller;

import be.digitalcity.spring.airport.bl.service.AirportService;
import be.digitalcity.spring.airport.pl.models.dto.AirportDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/airport")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<Page<AirportDTO>> getAll(@RequestParam int page){
        Page<AirportDTO> airports = airportService.getAll(page, 10).map( AirportDTO::toDTO );
        return ResponseEntity.ok( airports );
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<AirportDTO>> getAllSorted(){
        return ResponseEntity.ok(
                airportService.getAllSorted().stream()
                        .map(AirportDTO::toDTO)
                        .toList()
        );
    }

}
