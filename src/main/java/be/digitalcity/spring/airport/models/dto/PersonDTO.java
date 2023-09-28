package be.digitalcity.spring.airport.models.dto;

import be.digitalcity.spring.airport.models.entity.FidelityStatus;
import be.digitalcity.spring.airport.models.entity.Person;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {

    private long id;
    private String name;
    private FidelityStatus fidelity;

    public static PersonDTO toDto(Person person){
        if( person == null )
            return null;

        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getFirstname() + ' ' + person.getLastname())
                .fidelity(person.getFidelity())
                .build();
    }

}
