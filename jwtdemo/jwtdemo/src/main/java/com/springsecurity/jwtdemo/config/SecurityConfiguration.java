package com.springsecurity.jwtdemo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springsecurity.jwtdemo.service.CustomerUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
	@Autowired
	private CustomerUserDetailsService userService;
	@Autowired
	private JWTTokenHelper jWTTokenHelper;
	@Autowired
	private RestAuthenticationEntryPoint authenticationEntryPoint;
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
			throws Exception {
		return configuration.getAuthenticationManager();
	}
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.exceptionHandling((exceptionHandling)->exceptionHandling.authenticationEntryPoint(authenticationEntryPoint));
		http.sessionManagement((config)->config.sessionCreationPolicy(
				SessionCreationPolicy.STATELESS));
		http.authorizeHttpRequests((requests) -> {
		requests
				.requestMatchers("/public/**").permitAll()
				.requestMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated();
		});		
	    http.addFilterBefore(new JWTAuthenticationFilter(userService, jWTTokenHelper),
			UsernamePasswordAuthenticationFilter.class);
		http.csrf((csrf) -> csrf.disable());
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}
	


}
