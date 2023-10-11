package be.digitalcity.spring.airport.presentation;

import be.digitalcity.spring.airport.bl.exceptions.*;
import be.digitalcity.spring.airport.presentation.models.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ControllerAdvisor {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> handle(ResourceNotFoundException ex, HttpServletRequest request){

        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDTO body = ErrorDTO.builder(uri, receivedAt, status.value())
                .putError("message", ex.getMessage())
                .build();

        return ResponseEntity.status(status)
                .body(body);
    }


    @ExceptionHandler(ResourceNotAvailableException.class)
    public ResponseEntity<ErrorDTO> handle(ResourceNotAvailableException ex, HttpServletRequest request){
        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.CONFLICT;

        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        error.put("resourceType", ex.getResourceClass().getSimpleName());
        error.put("id", ex.getId());

        ErrorDTO body = ErrorDTO.builder(uri, receivedAt, status.value())
                .putError("resource_not_available", error)
                .build();

        return ResponseEntity.status(status)
                .body(body);
    }

    @ExceptionHandler(DateTooSoonException.class)
    public ResponseEntity<ErrorDTO> handle(DateTooSoonException ex, HttpServletRequest request){
        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        error.put("date", ex.getDate());
        error.put("minDate", ex.getMinDate());

        ErrorDTO body = ErrorDTO.builder(uri, receivedAt, status.value())
                .putError("date_too_soon", error)
                .build();

        return ResponseEntity.status(status)
                .body(body);
    }

    @ExceptionHandler(FlightDepartureArrivalException.class)
    public ResponseEntity<ErrorDTO> handle(FlightDepartureArrivalException ex, HttpServletRequest request){
        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        error.put("departure", ex.getDeparture());
        error.put("arrival", ex.getArrival());

        ErrorDTO body = ErrorDTO.builder(uri, receivedAt, status.value())
                .putError("flight_departure_arrival_invalid", error)
                .build();

        return ResponseEntity.status(status)
                .body(body);
    }

    @ExceptionHandler(FlightDestinationException.class)
    public ResponseEntity<ErrorDTO> handle(FlightDestinationException ex, HttpServletRequest request){
        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());

        ErrorDTO body = ErrorDTO.builder(uri, receivedAt, status.value())
                .putError("flight_destination_invalid", error)
                .build();

        return ResponseEntity.status(status)
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException ex, HttpServletRequest request){
        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        Map<String, Object> body = new HashMap<>();

        body.put("uri", uri);
        body.put("receivedAt", receivedAt);
        body.put("status", status);

        Map<String, Object> errors = new HashMap<>();

        List<Map<String, Object>> globalErrors = new ArrayList<>();

        ex.getGlobalErrors().forEach( globalError -> {
            Map<String, Object> error = new HashMap<>();
            error.put( "message", globalError.getDefaultMessage() );
            error.put( "codes", globalError.getCodes() );
            globalErrors.add(error);
        } );

        errors.put( "global_validation_errors", globalErrors );
        List<Map<String, Object>> fieldErrors = new ArrayList<>();

        ex.getFieldErrors().forEach( fieldError -> {
            Map<String, Object> error = new HashMap<>();
            error.put( "message", fieldError.getDefaultMessage() );
            error.put( "code", fieldError.getCode() );
            error.put( "rejectedValue", fieldError.getRejectedValue());
            error.put( "fieldName", fieldError.getField() );
            fieldErrors.add(error);
        } );

        errors.put( "field_validation_errors", fieldErrors);

        body.put("errors", errors);

        return ResponseEntity.badRequest()
                .body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(){
        return ResponseEntity.badRequest()
                .body("from ControllerAdvisor");
    }

}
