package be.digitalcity.spring.airport.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id", nullable = false)
    private Long id;

    @Column(name = "airplane_serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "airplane_construction_date", nullable = false)
    private LocalDate constructionDate;

    @OneToMany(mappedBy = "airplane")
    private List<Flight> flights;

}
