package be.digitalcity.spring.airport.pl.controller;

import be.digitalcity.spring.airport.bl.service.AuthService;
import be.digitalcity.spring.airport.domain.entity.Person;
import be.digitalcity.spring.airport.domain.entity.Role;
import be.digitalcity.spring.airport.pl.models.dto.AuthDTO;
import be.digitalcity.spring.airport.pl.models.form.LoginForm;
import be.digitalcity.spring.airport.utils.jwt.JWTProvider;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JWTProvider jwtProvider;

    public AuthController(AuthService authService, JWTProvider jwtProvider) {
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm form){
        Person connectedUser = authService.login(form.getUsername(), form.getPassword());
        String token = jwtProvider.generateToken( connectedUser );
        return ResponseEntity.ok(
                new AuthDTO(
                        form.getUsername(),
                        token,
                        connectedUser.getRoles().stream()
                                .map(Role::getName)
                                .toList()
                )
        );
    }

}
