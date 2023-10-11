package be.digitalcity.spring.airport.bl.service;

import be.digitalcity.spring.airport.domain.entity.Airport;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AirportService {

    List<Airport> getAllSorted();
    Page<Airport> getAll(int page, int countByPage);
    Airport getOne(Long id);

}
