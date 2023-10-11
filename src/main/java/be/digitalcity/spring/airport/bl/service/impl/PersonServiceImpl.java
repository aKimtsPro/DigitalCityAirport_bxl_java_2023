package be.digitalcity.spring.airport.bl.service.impl;

import be.digitalcity.spring.airport.bl.service.PersonService;
import be.digitalcity.spring.airport.dal.repository.PassengerRepository;
import be.digitalcity.spring.airport.domain.FidelityStatus;
import be.digitalcity.spring.airport.domain.entity.Passenger;
import be.digitalcity.spring.airport.domain.entity.Person;
import be.digitalcity.spring.airport.dal.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PassengerRepository passengerRepository;

    public PersonServiceImpl(PersonRepository personRepository,
                             PassengerRepository passengerRepository) {
        this.personRepository = personRepository;
        this.passengerRepository = passengerRepository;
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

    @Override
    public Page<Passenger> getPassengerByFidelity(FidelityStatus fidelity, int page) {
        return passengerRepository.findByStatus(fidelity, PageRequest.of(page-1, 20));
    }

    //    @Override
//    public void updateFidelity(long id, FidelityStatus fidelity) {
//        personRepository.updateFidelity(id, fidelity);
//    }
}
