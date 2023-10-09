package be.digitalcity.spring.airport.repository;


import be.digitalcity.spring.airport.models.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot, Long> {
}
