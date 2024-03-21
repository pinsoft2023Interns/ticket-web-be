package com.pinsoft.ticketwebbe.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/register","/authenticate","/**")
                .permitAll()
                //TODO authorization
                //example code for delete end
                /*.requestMatchers(DELETE, "/swagger-ui/user/**").hasAnyAuthority(ADMIN.name(), COMPANY_ADMIN.name())
                .requestMatchers(HttpMethod.POST).hasAuthority("admin , user")
                .requestMatchers(HttpMethod.PUT).hasAuthority("admin, user")

                .requestMatchers(DELETE, "/swagger-ui/admin/**").hasAuthority(ADMIN.name())
                .requestMatchers(HttpMethod.POST).hasAuthority("admin , user")
                .requestMatchers(HttpMethod.PUT).hasAuthority("admin, user")
                */
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
