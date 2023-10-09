package be.digitalcity.spring.airport.models.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id", nullable = false)
    private Long id;

    @Column(name = "flight_departure", nullable = false)
    private LocalDateTime departure;
    @Column(name = "flight_arrival", nullable = false)
    private LocalDateTime arrival;

    @Column(name = "flight_price", nullable = false)
    private double price;
    @Column(name = "flight_cancelled", nullable = false)
    private boolean cancelled = false;

    @ManyToOne
    @JoinColumn(name = "flight_airplane_id")
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "flight_pilot_id")
    private Pilot pilot;

    @ManyToOne
    @JoinColumn(name = "flight_airport_origin_id", nullable = false)
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "flight_airport_destination_id", nullable = false)
    private Airport destination;

    @OneToMany(mappedBy = "reservedFlight")
    private List<Reservation> reservations;

}
