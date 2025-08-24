package com.kgonzaga.note.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF para desarrollo (solo para APIs REST)
                .csrf(csrf -> csrf.disable())

                // 🔑 Esto permite los frames
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable())
                )

                // Permitir acceso a todas las URLs sin autenticación
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // Deshabilitar el formulario de login por defecto
                .formLogin(form -> form.disable())

                // Deshabilitar autenticación HTTP básica
                .httpBasic(basic -> basic.disable())

                // Deshabilitar logout (opcional)
                .logout(logout -> logout.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
