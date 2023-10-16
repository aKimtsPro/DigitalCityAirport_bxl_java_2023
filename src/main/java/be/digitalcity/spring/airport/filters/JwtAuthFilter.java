package be.digitalcity.spring.airport.filters;

import be.digitalcity.spring.airport.utils.jwt.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;

    public JwtAuthFilter(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1 - extraire le token
        String token = jwtProvider.extractToken(request);
        // 2 - valider le token (si il existe)
        UserDetails userDetails;
        if( token != null && (userDetails=jwtProvider.isTokenValid(token)) != null ) {
            // 3 - si il est valid, creer un objet Authentication
            Authentication auth = jwtProvider.generateAuth(userDetails);
            // 4 - mettre dans SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication( auth );
        }

        filterChain.doFilter(request, response);
    }
}
