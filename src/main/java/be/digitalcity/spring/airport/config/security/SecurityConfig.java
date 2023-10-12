package be.digitalcity.spring.airport.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableMethodSecurity
public class SecurityConfig {

// region EXAMPLE REQUEST MATCHERS
    //        http.authorizeHttpRequests(
//                registry -> registry
//                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
//                        .requestMatchers("/person/passenger").hasAnyRole("PASSENGER", "PILOT")
//                        .requestMatchers(HttpMethod.GET, "/flight/**").authenticated()
//                        .requestMatchers("/flight/*/p?lot", "/flight/*/airplane").anonymous()
//                        .requestMatchers( request -> request.getRequestURI().length() > 50 ).authenticated()
//                        .requestMatchers( request -> request.getRequestURI().length() > 50 ).anonymous()
//                        .requestMatchers( request -> request.getRequestURI().length() > 50 ).permitAll()
//                        .requestMatchers( request -> request.getRequestURI().length() > 50 ).denyAll()
//                        .requestMatchers( request -> request.getRequestURI().length() > 50 ).hasRole("ROLE")
//                        .requestMatchers( request -> request.getRequestURI().length() > 50 ).hasAnyRole("ROLE1", "ROLE2")
//                        .requestMatchers( request -> request.getRequestURI().length() > 50 ).hasAuthority("AUTHORITY")
//                        .requestMatchers( request -> request.getRequestURI().length() > 50 ).hasAnyAuthority("AUTHORITY1", "AUTHORITY2")
//        );
// endregion

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf( (csrfConfig) -> csrfConfig.disable() );

        http.sessionManagement(sessionManagConfig -> sessionManagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        http.authorizeHttpRequests(
                registry -> registry
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
        );

        return http.build();

    }

}
