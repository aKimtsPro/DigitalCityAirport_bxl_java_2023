package be.digitalcity.spring.airport.dal.repository;

import be.digitalcity.spring.airport.domain.entity.Role;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RoleRepository extends Repository<Role, Long> {

    Optional<Role> findByName(String name);

}
