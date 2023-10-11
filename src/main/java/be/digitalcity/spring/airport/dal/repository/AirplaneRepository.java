package be.digitalcity.spring.airport.dal.repository;

import be.digitalcity.spring.airport.domain.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    @Query("""
        SELECT a
        FROM Airplane a
        WHERE a.constructionDate > :reference
            AND a.serialNumber LIKE :startWith%
        ORDER BY a.serialNumber
    """)
    List<Airplane> findLatestPlane(LocalDate reference, String startWith);

    @Query(
        value = """
            SELECT *
            FROM airplane as a
            WHERE a.airplane_serial_number LIKE :start%
        """,
        nativeQuery = true
    )
    List<Airplane> findAllBySerialNumberStarts(@Param("start") String startsWith);

}
