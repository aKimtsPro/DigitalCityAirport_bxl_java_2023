package be.digitalcity.spring.airport.service.impl;

import be.digitalcity.spring.airport.exceptions.ResourceNotFoundException;
import be.digitalcity.spring.airport.models.entity.Airplane;
import be.digitalcity.spring.airport.repository.AirplaneRepository;
import be.digitalcity.spring.airport.service.AirplaneService;
import org.springframework.stereotype.Service;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public Airplane getOne(Long id) {
        return airplaneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Airplane.class, id));
    }
}
