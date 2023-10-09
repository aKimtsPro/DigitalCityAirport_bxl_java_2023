package be.digitalcity.spring.airport.service;

import be.digitalcity.spring.airport.models.entity.FidelityStatus;
import be.digitalcity.spring.airport.models.entity.Person;

import java.lang.reflect.Field;
import java.util.List;

public interface PersonService extends CrudService<Person, Long> {

    List<Person> getWithNameContaining(String search);

//    void updateFidelity(long id, FidelityStatus fidelity);

}
