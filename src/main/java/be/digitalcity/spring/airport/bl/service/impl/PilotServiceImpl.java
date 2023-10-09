package be.digitalcity.spring.airport.bl.service.impl;

import be.digitalcity.spring.airport.bl.service.PilotService;
import be.digitalcity.spring.airport.bl.exceptions.ResourceNotFoundException;
import be.digitalcity.spring.airport.models.entity.Pilot;
import be.digitalcity.spring.airport.dal.repository.PilotRepository;
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
