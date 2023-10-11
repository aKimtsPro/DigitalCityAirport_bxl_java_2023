package be.digitalcity.spring.airport.dal.repository;

import be.digitalcity.spring.airport.models.FidelityStatus;
import be.digitalcity.spring.airport.models.entity.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    @Query(
        value = """
            SELECT p
            FROM Passenger p
            WHERE p.status = :status
        """
    )
    Page<Passenger> findByStatus(FidelityStatus status, Pageable pageable);

}
