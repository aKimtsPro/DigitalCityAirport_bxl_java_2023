package be.digitalcity.spring.airport.bl.service;

import be.digitalcity.spring.airport.domain.entity.Reservation;

public interface ReservationService {

    Reservation getOne(long id);

    Reservation create(long flightId, long passengerId);

    void cancel(long id);

}
