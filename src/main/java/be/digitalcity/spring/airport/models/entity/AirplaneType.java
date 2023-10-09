package be.digitalcity.spring.airport.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirplaneType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id", nullable = false)
    private Long id;

    @Column(name = "type_model", nullable = false)
    private String model;

    @Column(name = "type_constructor", nullable = false)
    private String constructor;

    @Column(name = "type_capacity", nullable = false)
    private int capacity;

}
