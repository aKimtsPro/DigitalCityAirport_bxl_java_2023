package be.digitalcity.spring.airport.repository;

import be.digitalcity.spring.airport.models.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
