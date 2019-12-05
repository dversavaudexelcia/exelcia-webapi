package com.exelcia.webapi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exelcia.webapi.exceptions.ResourceNotFoundException;
import com.exelcia.webapi.model.User;
import com.exelcia.webapi.repository.UserRepository;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserRepository repo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = getJwtFromRequest(request);
		if(StringUtils.hasText(jwt) && tokenProvider.isValidateToken(jwt)) {
			
			User user = repo.findById(tokenProvider.getUserIdFromJwt(jwt)).orElseThrow(
					() -> new ResourceNotFoundException("User", "id", 0));
			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null);
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(auth);			
		}
		filterChain.doFilter(request, response);
		
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(StringUtils.containsWhitespace(bearerToken)) {
			return bearerToken.split(" ")[1];
			// return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
	
	

}
