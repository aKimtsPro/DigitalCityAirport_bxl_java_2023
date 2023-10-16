package be.digitalcity.spring.airport.pl.models.dto;

import java.util.List;

public record AuthDTO(
        String username,
        String token,
        List<String> roles
) {}
