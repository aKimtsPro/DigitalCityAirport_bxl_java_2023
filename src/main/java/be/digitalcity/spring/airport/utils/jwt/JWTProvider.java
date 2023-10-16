package be.digitalcity.spring.airport.utils.jwt;

import be.digitalcity.spring.airport.domain.entity.Person;
import be.digitalcity.spring.airport.domain.entity.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JWTProvider {

    private final UserDetailsService userDetailsService;

    public JWTProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(Person person){
        return JWT.create()
                .withSubject(person.getUsername())
                .withExpiresAt(Instant.now().plusMillis(86_400_000)) // 1 jour
                .withClaim(
                        "roles",
                        person.getRoles().stream()
                                .map(Role::getName)
                                .toList()
                )
                .sign(Algorithm.HMAC512("#RqzSTh77B29z@vj})Pe6T;Vaoz6jc;~wtG(Bzavk,L#IyokJT"));
    }

    public String extractToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if( authHeader == null || !authHeader.startsWith("Bearer ") )
            return null;

        return authHeader.replace("Bearer ", "");
    }

    public UserDetails isTokenValid(String token){

        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("#RqzSTh77B29z@vj})Pe6T;Vaoz6jc;~wtG(Bzavk,L#IyokJT"))
                .acceptExpiresAt(86_400_000)
//                .withClaim("roles", (claim, decodedJWT) -> {
//                    List<String> value = claim.asList(String.class);
//                    return value.isEmpty();
//                })
                .build();

        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            return userDetailsService.loadUserByUsername(username);
        }
        catch (JWTVerificationException ex){
            return null;
        }

    }

    public Authentication generateAuth(UserDetails userDetails){
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }

}
