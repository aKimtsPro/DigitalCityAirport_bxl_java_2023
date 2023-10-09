package be.digitalcity.spring.airport.controller;

import be.digitalcity.spring.airport.exceptions.ResourceNotFoundException;
import be.digitalcity.spring.airport.models.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){

        String uri = request.getRequestURI();
        LocalDateTime receivedAt = LocalDateTime.now();
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDTO body = ErrorDTO.builder(uri, receivedAt, status.value())
                .putError("message", ex.getMessage())
                .build();

        return ResponseEntity.status(status)
                .body(body);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(){
        return ResponseEntity.badRequest()
                .body("from ControllerAdvisor");
    }

}
