package com.maddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {

	@Bean
	UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(getUserDetailsService());
		authProvider.setPasswordEncoder(bCryptPasswordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManage(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();

	}

	// Cofiguration Code

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				 .requestMatchers("/user/**").hasRole("USER") 
		.requestMatchers("/**").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/signin")
				.defaultSuccessUrl("/user/dashboard")
				.and().csrf().disable();
		
		http.authenticationProvider(authenticationProvider());
		return http.build();
	}

}
