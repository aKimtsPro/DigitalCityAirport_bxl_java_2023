package be.digitalcity.spring.airport.bl.service;

import be.digitalcity.spring.airport.models.entity.Person;

import java.util.List;

public interface PersonService extends CrudService<Person, Long> {

    List<Person> getWithNameContaining(String search);

//    void updateFidelity(long id, FidelityStatus fidelity);

}
