package be.digitalcity.spring.airport.service.impl;

import be.digitalcity.spring.airport.models.entity.FidelityStatus;
import be.digitalcity.spring.airport.models.entity.Person;
import be.digitalcity.spring.airport.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl implements PersonService {


    private static long nextID = 1;
    private final Map<Long, Person> personMap = new HashMap<>();


    @Override
    public List<Person> getAll() {
        return new ArrayList<>(personMap.values());
    }

    @Override
    public Person getOne(Long id) {
        if( personMap.containsKey( id ) )
            return personMap.get(id);

        throw new IllegalArgumentException("no element with this id");
    }

    @Override
    public void insert(Person entity) {
        entity.setId(nextID++);
        personMap.put( entity.getId(), entity );
    }

    @Override
    public void delete(Long id) {
        if( !personMap.containsKey(id) )
            throw new IllegalArgumentException("no element with this id");

        personMap.remove(id);
    }

    @Override
    public Person update(Long id, Person entity) {
        if( !personMap.containsKey(id) )
            throw new IllegalArgumentException("no element with this id");

        entity.setId(id);
        personMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Person> getWithNameContaining(String search) {
        return personMap.values().stream()
                .filter( e -> e.getFirstname().contains( search ) || e.getLastname().contains( search ) )
                .toList();
    }

    @Override
    public void updateFidelity(long id, FidelityStatus fidelity) {
        Person toUpdate = this.getOne( id );
        toUpdate.setFidelity( fidelity );
    }
}
