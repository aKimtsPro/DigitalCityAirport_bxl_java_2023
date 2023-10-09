package be.digitalcity.spring.airport.service;

import be.digitalcity.spring.airport.models.entity.Airplane;

public interface AirplaneService {
    Airplane getOne(Long id);
}
