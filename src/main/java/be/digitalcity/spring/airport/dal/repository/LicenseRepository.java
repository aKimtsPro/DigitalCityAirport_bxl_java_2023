package be.digitalcity.spring.airport.dal.repository;

import be.digitalcity.spring.airport.domain.entity.License;

import java.util.Collection;
import java.util.Optional;

public interface LicenseRepository extends GetterRepository<License, Long> {

    Optional<License> findByAirplaneType_ModelIn(Collection<String> models);

}
