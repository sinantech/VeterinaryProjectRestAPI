package dev.patika.vetapp.v1.core.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder().username("john").password("{noop}test123").roles("employee").build();
        UserDetails charles = User.builder().username("charles").password("{noop}test123").roles("employee", "manager").build();
        UserDetails max = User.builder().username("max").password("{noop}test123").roles("employee", "manager", "admin").build();
        return new InMemoryUserDetailsManager(john, charles, max);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/v1/customers").hasRole("employee")
                        .requestMatchers(HttpMethod.GET, "/v1/customers/**").hasRole("employee")
                        .requestMatchers(HttpMethod.POST, "/v1/customers").hasRole("manager")
                        .requestMatchers(HttpMethod.PUT, "/v1/customers").hasRole("manager")
                        .requestMatchers(HttpMethod.DELETE, "/v1/customers/**").hasRole("admin")
        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}
