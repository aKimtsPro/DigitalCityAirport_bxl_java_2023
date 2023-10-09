package be.digitalcity.spring.airport.service;

import be.digitalcity.spring.airport.models.entity.Reservation;

public interface ReservationService {

    Reservation getOne(long id);

    Reservation create(long flightId, long passengerId);

    void cancel(long id);

}
