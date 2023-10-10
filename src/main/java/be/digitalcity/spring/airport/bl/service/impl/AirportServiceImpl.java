package be.digitalcity.spring.airport.bl.service.impl;

import be.digitalcity.spring.airport.bl.exceptions.ResourceNotFoundException;
import be.digitalcity.spring.airport.models.entity.Airport;
import be.digitalcity.spring.airport.dal.repository.AirportRepository;
import be.digitalcity.spring.airport.bl.service.AirportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Page<Airport> getAll(int page, int countByPage) {
        return airportRepository.findAll(PageRequest.of(page, countByPage).withSort(Sort.by("name")));
    }

    @Override
    public List<Airport> getAllSorted() {
        return airportRepository.findAll(
                Sort.by("name").ascending()
                        .and(Sort.by("address"))
        );
    }
}
