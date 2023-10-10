package be.digitalcity.spring.airport.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "license_id", nullable = false)
    private Long id;
    @Column(name = "license_acquired_at", nullable = false)
    private LocalDate acquiredAt;
    @Column(name = "license_name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "license_type_id", nullable = false)
    private AirplaneType airplaneType;

}
