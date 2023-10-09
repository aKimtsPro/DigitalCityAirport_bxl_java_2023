package be.digitalcity.spring.airport.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false)
    private Long id;

    @Column(name = "reservation_price", nullable = false)
    private double price;

    @Column(name = "reservation_cancelled", nullable = false)
    private boolean cancelled = false;

    @Column(name= "reservation_created_at", nullable = false)
    private LocalDateTime createdAd;

    @ManyToOne
    @JoinColumn(name = "reservation_flight_id", nullable = false)
    private Flight reservedFlight;

    @ManyToOne
    @JoinColumn(name = "reservation_passenger_id", nullable = false)
    private Passenger passenger;
}
