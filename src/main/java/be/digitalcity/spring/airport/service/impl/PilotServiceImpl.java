package be.digitalcity.spring.airport.service.impl;

import be.digitalcity.spring.airport.exceptions.ResourceNotFoundException;
import be.digitalcity.spring.airport.models.entity.Pilot;
import be.digitalcity.spring.airport.repository.PilotRepository;
import be.digitalcity.spring.airport.service.PilotService;
import org.springframework.stereotype.Service;

@Service
public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;

    public PilotServiceImpl(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    @Override
    public Pilot getOne(Long id) {
        return pilotRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(Pilot.class, id));
    }
}
