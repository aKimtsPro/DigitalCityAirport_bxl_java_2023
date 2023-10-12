package be.digitalcity.spring.airport.pl.models.form;

import be.digitalcity.spring.airport.domain.FidelityStatus;
import be.digitalcity.spring.airport.domain.entity.Person;
import be.digitalcity.spring.airport.pl.validation.constraint.StartsWithMaj;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonForm {

    @NotBlank
    @StartsWithMaj(message = "firstname must have 5 capitals to start", numberCapital = 5)
    @Size(min = 5)
    private String firstname;
    @NotBlank
    @StartsWithMaj
    private String lastname;

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}$")
    private String password;



    public Person toEntity(){

        Person p = new Person();
        p.setFirstname(firstname);
        p.setLastname(lastname);
        p.setUsername(username);
        p.setPassword(password);
        return p;

    }

}
