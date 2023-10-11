package be.digitalcity.spring.airport.domain.entity;

import be.digitalcity.spring.airport.domain.FidelityStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger extends Person {

    @Enumerated(EnumType.STRING)
    @Column(name = "passenger_fidelity", nullable = false)
    private FidelityStatus status = FidelityStatus.NONE;

    @OneToMany(mappedBy = "passenger")
    private List<Reservation> reservations;

}
