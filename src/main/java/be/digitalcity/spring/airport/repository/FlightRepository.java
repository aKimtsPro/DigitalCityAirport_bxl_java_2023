package be.digitalcity.spring.airport.repository;

import be.digitalcity.spring.airport.models.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
