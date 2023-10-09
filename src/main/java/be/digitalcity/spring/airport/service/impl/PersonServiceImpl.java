package be.digitalcity.spring.airport.service.impl;

import be.digitalcity.spring.airport.models.entity.FidelityStatus;
import be.digitalcity.spring.airport.models.entity.Person;
import be.digitalcity.spring.airport.repository.PersonRepository;
import be.digitalcity.spring.airport.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getOne(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("no resource with this ID"));
    }

    @Override
    public void insert(Person entity) {
        entity.setId(0L);
        personRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person update(Long id, Person entity) {
        if( !personRepository.existsById(id) )
            throw new IllegalArgumentException("no resource with this ID");

        entity.setId(id);
        return personRepository.save(entity);
    }

    @Override
    public List<Person> getWithNameContaining(String search) {
        return personRepository.findByNameContaining(search);
    }

//    @Override
//    public void updateFidelity(long id, FidelityStatus fidelity) {
//        personRepository.updateFidelity(id, fidelity);
//    }
}
