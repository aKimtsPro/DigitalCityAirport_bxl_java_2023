package be.digitalcity.spring.airport.dal.repository;

import be.digitalcity.spring.airport.models.entity.Flight;
import be.digitalcity.spring.airport.models.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByPassenger_IdAndReservedFlight_Id(long passengerId, long flightId);

    // TODO fix
    @Query(""" 
        SELECT r.reservedFlight
        FROM Reservation r
        WHERE r.passenger.id = :userId and
            (:seeCancelled = true and not r.reservedFlight.cancelled)
    """)
    public List<Flight> getFlightsReservedBy(long userId, boolean seeCancelled);

}
