package be.digitalcity.spring.airport.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pilot extends Person{

    @OneToMany(mappedBy = "pilot")
    private List<Flight> flights;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>(super.getAuthorities());
        authorities.add(new SimpleGrantedAuthority("ROLE_PILOT"));
        return authorities;
    }

}
