package be.digitalcity.spring.airport.repository;

import be.digitalcity.spring.airport.models.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
}
