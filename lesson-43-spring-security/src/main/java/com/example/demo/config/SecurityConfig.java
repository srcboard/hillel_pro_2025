package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/public").permitAll()
						.requestMatchers("/user").hasRole("USER")
						.requestMatchers("/admin").hasRole("ADMIN")
						.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults());
		return httpSecurity.build();
	}

	@Bean
	public UserDetailsService users() {
		return new InMemoryUserDetailsManager(
				User.withUsername("user").password("{noop}password").roles("USER").build(),
				User.withUsername("admin").password("{noop}password").roles("ADMIN").build());
	}
}
