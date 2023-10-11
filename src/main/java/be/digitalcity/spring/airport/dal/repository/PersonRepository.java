package be.digitalcity.spring.airport.dal.repository;

import be.digitalcity.spring.airport.models.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

//    List<Person> findAllByFirstnameContainingOrLastnameContaining(String search, String search2);

    @Query("""
        SELECT p
        FROM Person p
        WHERE
            p.firstname LIKE %?1% OR
            p.lastname LIKE %?1%
    """)
    List<Person> findByNameContaining(String containing);

//    @Transactional
//    @Modifying
//    @Query("""
//        UPDATE Person p
//        SET p.fidelity = :fidelity
//        WHERE p.id = :id
//    """)
//    void updateFidelity(long id, FidelityStatus fidelity);

}
