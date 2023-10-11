package be.digitalcity.spring.airport.bl.service.impl;

import be.digitalcity.spring.airport.bl.exceptions.ResourceNotFoundException;
import be.digitalcity.spring.airport.domain.entity.Flight;
import be.digitalcity.spring.airport.domain.entity.Passenger;
import be.digitalcity.spring.airport.domain.entity.Reservation;
import be.digitalcity.spring.airport.dal.repository.FlightRepository;
import be.digitalcity.spring.airport.dal.repository.PassengerRepository;
import be.digitalcity.spring.airport.dal.repository.ReservationRepository;
import be.digitalcity.spring.airport.bl.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(PassengerRepository passengerRepository,
                                  FlightRepository flightRepository,
                                  ReservationRepository reservationRepository) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation getOne(long id){
        return reservationRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(Reservation.class, id) );
    }

    @Override
    public Reservation create(long flightId, long passengerId) {

        if( reservationRepository.existsByPassenger_IdAndReservedFlight_Id(passengerId, flightId) )
            throw new IllegalArgumentException(); // TODO specify

        Reservation reservation = new Reservation();
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException(Passenger.class, passengerId));

        Flight flight = flightRepository.findById(flightId)
                .orElseThrow( () -> new ResourceNotFoundException(Flight.class, flightId) );

        if( flight.getDeparture().isBefore( LocalDateTime.now().plusDays(3) ) )
            throw new IllegalArgumentException(); // TODO specify

        reservation.setPassenger( passenger );
        reservation.setReservedFlight( flight );
        reservation.setPrice( reservation.getReservedFlight().getPrice() );
        reservation.setCreatedAd( LocalDateTime.now() );

        return reservationRepository.save(reservation);
    }

    @Override
    public void cancel(long id) {
        Reservation reservation = getOne(id);

        // La reservation est déjà annulée
        if( reservation.isCancelled() )
            throw new RuntimeException(); // TODO specify
        // La reservation est pour moins de 3 jours dans le futur
        if( reservation.getReservedFlight().getDeparture().isBefore( LocalDateTime.now().plusDays(3) ) )
            throw new RuntimeException(); // TODO specify
        // Le vol lié est annulé
        if( reservation.getReservedFlight().isCancelled() )
            throw new RuntimeException(); // TODO specify

        reservation.setCancelled(true);
        reservationRepository.save(reservation);
    }
}
