package com.exelcia.webapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exelcia.webapi.security.JwtAuthenticationEntryPoint;
import com.exelcia.webapi.security.JwtAuthenticationFilter;
import com.exelcia.webapi.service.UserAuthService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthenticationEntryPoint unAuthorizeHandler;
	
	@Autowired
	private UserAuthService userAuth;
	
	
	@Bean
	public JwtAuthenticationFilter filter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.user
		auth.userDetailsService(userAuth).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
				.and()
			.csrf()
				.disable()
			.exceptionHandling()
				.authenticationEntryPoint(unAuthorizeHandler)
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				.antMatchers("/",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js",
						"/swagger-resources/**",
						"/swagger-ui.html",
						"/v2/api-docs",
						"/webjars/**").permitAll()
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/forfaits/**").permitAll()
				.anyRequest().authenticated();
						
		http.addFilterBefore(filter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	

}
