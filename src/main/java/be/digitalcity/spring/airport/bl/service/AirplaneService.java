package be.digitalcity.spring.airport.bl.service;

import be.digitalcity.spring.airport.models.entity.Airplane;

public interface AirplaneService {
    Airplane getOne(Long id);
}
