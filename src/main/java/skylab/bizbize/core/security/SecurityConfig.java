package skylab.bizbize.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import skylab.bizbize.business.abstracts.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final UserService userService;

    private final JwtAuthFilter jwtAuthFilter;

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserService userService, JwtAuthFilter jwtAuthFilter, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtAuthFilter = jwtAuthFilter;
        this.passwordEncoder = passwordEncoder;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(x ->
                        x.requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/users/**").hasAnyRole("ADMIN")
                                .requestMatchers("/api/events/getEvents").permitAll()
                                .requestMatchers("/api/events/getEventById/**").permitAll()
                                .requestMatchers("/api/events/getActiveEvent").permitAll()
                                .requestMatchers("/api/events/add").hasRole("ADMIN")
                                .requestMatchers("/api/photos/getPhotoById").permitAll()
                                .requestMatchers("/api/photos/getPhotosByEventId").permitAll()
                                .requestMatchers("/api/auth/generateToken").hasRole("ADMIN")
                                .requestMatchers("/api/auth/registerUser").hasRole("ADMIN")
                                .requestMatchers("/api/staff/getStaff").permitAll()
                                .requestMatchers("/api/staff/getStaffById").permitAll()
                                .requestMatchers("/api/staff/getStaffByFirstAndLastName").permitAll()
                                .requestMatchers("/api/staff/add").hasRole("ADMIN")


                )
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
