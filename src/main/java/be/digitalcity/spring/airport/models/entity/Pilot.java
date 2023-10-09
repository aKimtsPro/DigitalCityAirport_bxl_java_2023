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
public class Pilot extends Person{

    @OneToMany(mappedBy = "pilot")
    private List<Flight> flights;

}
