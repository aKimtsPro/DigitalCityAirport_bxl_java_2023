package be.digitalcity.spring.airport.pl.controller;

import be.digitalcity.spring.airport.bl.service.AirplaneService;
import be.digitalcity.spring.airport.pl.models.dto.AirplaneDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/airplane")
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping(params = "start")
    public ResponseEntity<List<AirplaneDTO>> getAllWithSerialNumberStartsBy(@RequestParam String start){
        return ResponseEntity.ok(
                airplaneService.getWithSerialNumberStarts(start).stream()
                        .map(AirplaneDTO::toDTO)
                        .toList()
        );
    }

    @GetMapping("/experienced")
    public ResponseEntity<List<AirplaneDTO>> getExperienced(){
        return ResponseEntity.ok(
                airplaneService.getExperienced().stream()
                        .map( AirplaneDTO::toDTO )
                        .toList()
        );
    }

}
