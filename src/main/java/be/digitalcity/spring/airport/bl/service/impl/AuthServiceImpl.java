package be.digitalcity.spring.airport.bl.service.impl;

import be.digitalcity.spring.airport.bl.service.AuthService;
import be.digitalcity.spring.airport.dal.repository.PersonRepository;
import be.digitalcity.spring.airport.domain.entity.Person;
import be.digitalcity.spring.airport.utils.jwt.JWTProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final PersonRepository personRepository;

    public AuthServiceImpl(AuthenticationManager authManager, PersonRepository personRepository) {
        this.authManager = authManager;
        this.personRepository = personRepository;
    }

    @Override
    public Person login(String username, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        auth = authManager.authenticate(auth);
        return personRepository.findByUsername(auth.getName())
                .orElseThrow();
    }
}
