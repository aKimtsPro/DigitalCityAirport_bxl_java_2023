package be.digitalcity.spring.airport.bl.service;

import be.digitalcity.spring.airport.domain.FidelityStatus;
import be.digitalcity.spring.airport.domain.entity.Passenger;
import be.digitalcity.spring.airport.domain.entity.Person;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonService extends CrudService<Person, Long> {

    List<Person> getWithNameContaining(String search);

    Page<Passenger> getPassengerByFidelity(FidelityStatus fidelity, int page);
//    void updateFidelity(long id, FidelityStatus fidelity);

}
