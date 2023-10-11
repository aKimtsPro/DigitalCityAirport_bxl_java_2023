package be.digitalcity.spring.airport.dal.repository;

import be.digitalcity.spring.airport.models.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

//    @Query("""
//        SELECT f
//        FROM Flight f
//        WHERE f.departure BETWEEN :start AND :end
//    """)
    List<Flight> findByDepartureBetween(LocalDateTime start, LocalDateTime end);

    @Query("""
        SELECT f
        FROM Flight f
        WHERE
            (:min IS NULL OR f.price >= :min) AND
            (:max IS NULL OR f.price <= :max)
    """)
    List<Flight> findByPrice(Double min, Double max);

    @Query("""
        SELECT f
        FROM Flight f
        WHERE YEAR(current_date) - YEAR(f.airplane.constructionDate) <= 10
    """)
    List<Flight> findByAirplaneNew();

    @Query("""
        SELECT f
        FROM Flight f
            JOIN f.airplane a
        WHERE (
            SELECT COUNT(flight)
            FROM Flight flight
            WHERE flight.airplane.id = a.id AND
                flight.arrival < current_date
        ) > 10
    """)
    List<Flight> findWithPlaneExperienced();

}
