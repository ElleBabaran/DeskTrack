package io.reflectoring.demo.config;

import io.reflectoring.demo.service.CustomerDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomerDetailsService userDetailsService;

    public SecurityConfig(CustomerDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/","/login/**", "/index/**",
                                        "/signin/**","/forgot-password/**", "/user/**",  "/dashboard/**", "/status/**",
                                        "/admin/**", "/admintable/**" , "/css/**", "/js/**", "/img/**").permitAll()
                                .anyRequest().authenticated()
                ).formLogin(form ->form
                .loginPage("/login")
                        .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/homepage", true)
                                .permitAll()
                )
                .logout(Customizer.withDefaults());
        return http.build();
    }
}
