package be.digitalcity.spring.airport.models.entity;

import lombok.Data;

@Data
public class Person {

    private long id;
    private String firstname;
    private String lastname;
    private FidelityStatus fidelity;

}
