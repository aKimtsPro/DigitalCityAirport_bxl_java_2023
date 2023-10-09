package be.digitalcity.spring.airport.dal.repository;

import be.digitalcity.spring.airport.models.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
