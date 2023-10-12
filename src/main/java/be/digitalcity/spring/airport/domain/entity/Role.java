package be.digitalcity.spring.airport.domain.entity;

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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String name;
    @Column(name = "role_description")
    private String description;

    // Je ne veux pas être en bidirectionnel
//    @ManyToMany(mappedBy = "roles")
//    private List<Person> users;

}
