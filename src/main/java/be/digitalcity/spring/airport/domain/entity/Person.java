package be.digitalcity.spring.airport.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Long id;
    @Column(name = "person_firstname", nullable = false)
    private String firstname;
    @Column(name = "person_lastname", nullable = false)
    private String lastname;

}
