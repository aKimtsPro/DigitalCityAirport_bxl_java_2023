package be.digitalcity.spring.airport.bl.service.impl;

import be.digitalcity.spring.airport.bl.service.PersonService;
import be.digitalcity.spring.airport.dal.repository.PassengerRepository;
import be.digitalcity.spring.airport.dal.repository.RoleRepository;
import be.digitalcity.spring.airport.domain.FidelityStatus;
import be.digitalcity.spring.airport.domain.entity.Passenger;
import be.digitalcity.spring.airport.domain.entity.Person;
import be.digitalcity.spring.airport.dal.repository.PersonRepository;
import be.digitalcity.spring.airport.domain.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final PersonRepository personRepository;
    private final PassengerRepository passengerRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public PersonServiceImpl(
            PersonRepository personRepository,
             PassengerRepository passengerRepository,
             PasswordEncoder encoder,
            RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.passengerRepository = passengerRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
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

        entity.setPassword( encoder.encode(entity.getPassword()) );
        entity.getRoles().add(
                roleRepository.findByName("USER").orElseThrow()
        );

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }
}
