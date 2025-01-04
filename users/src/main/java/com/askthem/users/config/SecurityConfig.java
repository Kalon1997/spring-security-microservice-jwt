package com.askthem.users.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.askthem.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig{
	
//	@Autowired
//	private UserDetailsService userDetailsService;
//	
//	@Autowired
//	JwtFilter jwtFilter;
//	
//	@Autowired
//	private UserRepository userRepository;
	
	private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(customizer -> customizer.disable())
			.authorizeHttpRequests(request -> request
					.requestMatchers("/auth/loginuser", "/auth/registeruser")
					.permitAll()
					.requestMatchers("/auth/admin/**")
//					.hasRole("ADMIN")
					.hasAuthority("ADMIN")
					.requestMatchers("/auth/user/**")
//					.hasRole("USER")
					.hasAuthority("USER")
					.anyRequest().authenticated())
//			.httpBasic(Customizer.withDefaults())
		    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .authenticationProvider(authenticationProvider)
		    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		    .build();
		
//		http.formLogin(Customizer.withDefaults());
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user1 = User
//				.withDefaultPasswordEncoder()
//				.username("user")
//				.password("user")
//				.roles("USER")
//				.build();
//		UserDetails user2 = User
//				.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("admin")
//				.roles("ADMIN")
//				.build();
//		return new InMemoryUserDetailsManager(user1, user2);
//	}
	
	
//	@Bean
//    public UserDetailsService userDetailsService() {
//        return username -> userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//	
//	@Bean
//	public AuthenticationProvider authenticationProvider(){
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(new BCryptPasswordEncoder(8));
//		provider.setUserDetailsService(userDetailsService);
//		return provider; 
//	}
//	
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//		return config.getAuthenticationManager();
//	}
}
