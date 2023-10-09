package be.digitalcity.spring.airport.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id", nullable = false)
    private Long id;

    @Column(name = "airport_name", nullable = false)
    private String name;

    @Column(name = "airport_address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "destination")
    private List<Flight> arrivingFlights;
    @OneToMany(mappedBy = "origin")
    private List<Flight> departingFlights;

}
