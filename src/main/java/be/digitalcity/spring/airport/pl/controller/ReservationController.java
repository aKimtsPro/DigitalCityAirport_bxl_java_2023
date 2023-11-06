package be.digitalcity.spring.airport.pl.controller;

import be.digitalcity.spring.airport.pl.models.dto.ReservationDTO;
import be.digitalcity.spring.airport.domain.entity.Reservation;
import be.digitalcity.spring.airport.pl.models.form.ReservationForm;
import be.digitalcity.spring.airport.bl.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id:^[0-9]+$}")
    public ResponseEntity<ReservationDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok( ReservationDTO.toDTO(reservationService.getOne(id)) );
    }

    @PostMapping
    @PreAuthorize("hasRole('PASSENGER')")
    public ResponseEntity<ReservationDTO> create(Authentication auth, @RequestParam long flightId){
        String username = auth.getName();
        Reservation reservation = reservationService.create(flightId, username);
        return ResponseEntity.ok( ReservationDTO.toDTO( reservation ) );
    }

    @PatchMapping("/{id:^[0-9]+$}/cancel")
    public ResponseEntity<?> cancel(@PathVariable long id){
        reservationService.cancel(id);
        return ResponseEntity.noContent()
                .build();
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body( ex.getMessage() );
//    }

}
