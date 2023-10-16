package be.digitalcity.spring.airport.bl.service;

import be.digitalcity.spring.airport.domain.entity.Person;

public interface AuthService {

    Person login(String username, String password);

}
