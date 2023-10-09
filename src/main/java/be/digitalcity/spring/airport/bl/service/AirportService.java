package be.digitalcity.spring.airport.bl.service;

import be.digitalcity.spring.airport.models.entity.Airport;

public interface AirportService {

    Airport getOne(Long id);

}
