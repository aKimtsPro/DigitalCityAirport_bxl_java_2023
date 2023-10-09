package be.digitalcity.spring.airport.models.form;

import be.digitalcity.spring.airport.models.entity.FidelityStatus;
import be.digitalcity.spring.airport.models.entity.Person;
import lombok.Data;

@Data
public class PersonForm {

    private String firstname;
    private String lastname;
    private FidelityStatus fidelity;

    public Person toEntity(){

        Person p = new Person();
        p.setFirstname(firstname);
        p.setLastname(lastname);
        return p;

    }

}
