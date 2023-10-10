package be.digitalcity.spring.airport.bl.service.impl;

import be.digitalcity.spring.airport.bl.service.AirplaneService;
import be.digitalcity.spring.airport.bl.exceptions.ResourceNotFoundException;
import be.digitalcity.spring.airport.models.entity.Airplane;
import be.digitalcity.spring.airport.dal.repository.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Airplane> getWithSerialNumberStarts(String start) {
        return airplaneRepository.findAllBySerialNumberStarts(start);
    }
}
