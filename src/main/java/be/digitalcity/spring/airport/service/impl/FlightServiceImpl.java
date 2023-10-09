package be.digitalcity.spring.airport.service.impl;

import be.digitalcity.spring.airport.exceptions.*;
import be.digitalcity.spring.airport.models.entity.Airplane;
import be.digitalcity.spring.airport.models.entity.Flight;
import be.digitalcity.spring.airport.models.entity.Pilot;
import be.digitalcity.spring.airport.repository.AirplaneRepository;
import be.digitalcity.spring.airport.repository.FlightRepository;
import be.digitalcity.spring.airport.repository.PilotRepository;
import be.digitalcity.spring.airport.repository.ReservationRepository;
import be.digitalcity.spring.airport.service.FlightService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final PilotRepository pilotRepository;
    private final AirplaneRepository airplaneRepository;
    private final ReservationRepository reservationRepository;

    public FlightServiceImpl(FlightRepository flightRepository,
                             PilotRepository pilotRepository,
                             AirplaneRepository airplaneRepository,
                             ReservationRepository reservationRepository) {
        this.flightRepository = flightRepository;
        this.pilotRepository = pilotRepository;
        this.airplaneRepository = airplaneRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Flight getOne(long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Flight.class, id));
    }

    @Override
    public Flight create(Flight toCreate) {
        if( !toCreate.getArrival().isAfter( toCreate.getDeparture() ) )
            throw new FlightDepartureArrivalException(toCreate.getDeparture(), toCreate.getArrival());

        LocalDateTime minDate = LocalDateTime.now().plusDays(10);
        if( toCreate.getDeparture().isBefore(minDate) )
            throw new DateTooSoonException(toCreate.getDeparture(), minDate, "departure");

        if(Objects.equals(toCreate.getOrigin().getId(), toCreate.getDestination().getId()))
            throw new FlightDestinationException();

        if(isPilotUnavailable(toCreate.getPilot(), toCreate.getDeparture(), toCreate.getArrival()))
            throw new ResourceNotAvailableException(Pilot.class, toCreate.getPilot().getId(), "pilot is already booked for this time period");

        boolean noAirplaneConflict = toCreate.getAirplane().getFlights().stream()
                .allMatch( flight ->  toCreate.getArrival().isBefore( flight.getDeparture() ) || flight.getArrival().isBefore(toCreate.getDeparture()));

        if( !noAirplaneConflict )
            throw new ResourceNotAvailableException(Airplane.class, toCreate.getAirplane().getId(), "airplane already booked for this time period");

        return flightRepository.save( toCreate );
    }

    @Override
    public void updatePilot(long id, long pilotId) {
        Pilot newPilot = pilotRepository.findById(pilotId)
                .orElseThrow( () -> new ResourceNotFoundException(Pilot.class, pilotId) );

        Flight flight = getOne(id);

        if(isPilotUnavailable(newPilot, flight.getDeparture(), flight.getArrival()))
            throw new ResourceNotAvailableException(Pilot.class, pilotId, "pilot is already booked for this time period");

        flight.setPilot( newPilot );
        flightRepository.save( flight );
    }

    @Override
    public void updateAirplane(long id, long airplaneId) {
        Airplane newAirplane = airplaneRepository.findById(airplaneId)
                .orElseThrow( () -> new ResourceNotFoundException(Airplane.class, airplaneId) );

        Flight flight = getOne(id);

        if( isAirplaneUnavailable( newAirplane, flight.getDeparture(), flight.getArrival() ) )
            throw new ResourceNotAvailableException(Airplane.class, airplaneId, "airplane already booked for this time period");


        flight.setAirplane(newAirplane);
        flightRepository.save(flight);
    }

    private boolean isPilotUnavailable(Pilot pilot, LocalDateTime start, LocalDateTime end){
        return !pilot.getFlights().stream()
                .allMatch(flight -> flight.getDeparture().isAfter(end) || flight.getArrival().isBefore(start));
    }

    private boolean isAirplaneUnavailable(Airplane airplane, LocalDateTime start, LocalDateTime end) {
        return !airplane.getFlights().stream()
                .allMatch(flight -> flight.getDeparture().isAfter(end) || flight.getArrival().isBefore(start));
    }

    @Override
    public List<Flight> getUserFlights(long userId, boolean seeCancelled) {
        return reservationRepository.getFlightsReservedBy(userId, seeCancelled);
    }
}
