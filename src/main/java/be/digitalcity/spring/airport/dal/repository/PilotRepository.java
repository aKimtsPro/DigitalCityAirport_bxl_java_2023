package be.digitalcity.spring.airport.dal.repository;


import be.digitalcity.spring.airport.domain.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot, Long> {
}
