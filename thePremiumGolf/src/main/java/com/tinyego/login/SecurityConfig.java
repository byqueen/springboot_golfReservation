package com.tinyego.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();		
	}
	
	@Autowired
	SecurityUserDetailService securityUserDetail;
	
	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize ->authorize
			.requestMatchers("/member/list"
						   , "/golf/list"
						   , "/golf/product/list").hasAnyRole("ADMIN", "MANAGER")
	        .requestMatchers("/golf/course/list"
	        			   , "/golf/cart/edit"
	        			   , "/golf/reserve/list").authenticated()
	        .anyRequest().permitAll())
				
		.csrf(csrf ->csrf.disable())	
	    
		.formLogin(login ->login.loginPage("/member/form").defaultSuccessUrl("/member/success", true))
		.exceptionHandling(handling ->handling.accessDeniedPage("/member/accessDenied"))
		.logout(logout ->logout.invalidateHttpSession(true).logoutSuccessUrl("/"))
		.userDetailsService(securityUserDetail);
		return http.build();
	}	
}






















