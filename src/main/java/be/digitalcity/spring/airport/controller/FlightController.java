package be.digitalcity.spring.airport.controller;

import be.digitalcity.spring.airport.exceptions.ResourceNotFoundException;
import be.digitalcity.spring.airport.models.dto.FlightDTO;
import be.digitalcity.spring.airport.models.entity.Flight;
import be.digitalcity.spring.airport.models.form.FlightCreateForm;
import be.digitalcity.spring.airport.service.AirplaneService;
import be.digitalcity.spring.airport.service.AirportService;
import be.digitalcity.spring.airport.service.FlightService;
import be.digitalcity.spring.airport.service.PilotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final PilotService pilotService;
    private final AirportService airportService;
    private final AirplaneService airplaneService;
    private final FlightService flightService;

    public FlightController(PilotService pilotService, AirportService airportService, AirplaneService airplaneService, FlightService flightService) {
        this.pilotService = pilotService;
        this.airportService = airportService;
        this.airplaneService = airplaneService;
        this.flightService = flightService;
    }


    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<FlightDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok(
                FlightDTO.toDTO( flightService.getOne(id) )
        );
    }

    @GetMapping("/by_user")
    public ResponseEntity<List<FlightDTO>> getUserFlights(long userId, boolean seeCancelled){
        return ResponseEntity.ok(
                flightService.getUserFlights(userId, seeCancelled).stream()
                        .map( FlightDTO::toDTO )
                        .toList()
        );
    }


    // POST - http://localhost:8080/flight
    @PostMapping
    public ResponseEntity<FlightDTO> create(@RequestBody FlightCreateForm form){

        Flight flight = form.toEntity();

        flight.setPilot( pilotService.getOne(form.getPilotId()) );
        flight.setOrigin( airportService.getOne(form.getOriginId()) );
        flight.setDestination( airportService.getOne(form.getDestinationId()) );
        flight.setAirplane( airplaneService.getOne(form.getAirplaneId()) );

        FlightDTO body = FlightDTO.toDTO( flightService.create(flight) );

        return ResponseEntity.ok(body);
    }

    @PatchMapping("/{id:^[0-9]+$}/pilot")
    public ResponseEntity<?> updatePilot(@PathVariable long id, @RequestParam long pilotId){
        flightService.updatePilot(id, pilotId);
        return ResponseEntity.noContent()
                .build();
    }

    @PatchMapping("/{id:^[0-9]+$}/airplane")
    public ResponseEntity<?> updateAirplane(@PathVariable long id, @RequestParam long airplaneId){
        flightService.updateAirplane(id, airplaneId);
        return ResponseEntity.noContent()
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("handled in controller: " +  ex.getMessage() );
    }

}