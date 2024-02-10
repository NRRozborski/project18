package com.laguna.project18.WallapopGratuito.auth;

import com.laguna.project18.WallapopGratuito.auth.service.UserDetailService;
import com.laguna.project18.WallapopGratuito.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/callback").permitAll()
//                                .requestMatchers("/oauth2/authorization/google").permitAll()
//                        .requestMatchers("/category/all").permitAll()
                        .anyRequest().permitAll()
                )
                .userDetailsService(userDetailsService())
                .sessionManagement(session ->  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(config -> config.redirectionEndpoint(conf -> conf.baseUri("http://localhost/callback")))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailService(userRepository);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
