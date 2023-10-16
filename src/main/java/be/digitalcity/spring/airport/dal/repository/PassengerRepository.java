package be.digitalcity.spring.airport.dal.repository;

import be.digitalcity.spring.airport.domain.FidelityStatus;
import be.digitalcity.spring.airport.domain.entity.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query(
        value = """
            SELECT p
            FROM Passenger p
            WHERE p.status = :status
        """
    )
    Page<Passenger> findByStatus(FidelityStatus status, Pageable pageable);

    Optional<Passenger> findByUsername(String username);

}
