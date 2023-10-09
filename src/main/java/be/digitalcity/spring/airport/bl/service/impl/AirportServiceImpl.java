package be.digitalcity.spring.airport.bl.service.impl;

import be.digitalcity.spring.airport.bl.exceptions.ResourceNotFoundException;
import be.digitalcity.spring.airport.models.entity.Airport;
import be.digitalcity.spring.airport.dal.repository.AirportRepository;
import be.digitalcity.spring.airport.bl.service.AirportService;
import org.springframework.stereotype.Service;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public Airport getOne(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Airport.class, id));
    }
}
