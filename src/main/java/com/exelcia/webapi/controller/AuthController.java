package com.exelcia.webapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exelcia.webapi.model.Forfait;
import com.exelcia.webapi.model.User;
import com.exelcia.webapi.payload.SigninResponse;
import com.exelcia.webapi.payload.SigninRequest;
import com.exelcia.webapi.repository.RoleRepository;
import com.exelcia.webapi.repository.UserRepository;
import com.exelcia.webapi.security.CurrentUser;
import com.exelcia.webapi.security.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;

@RestController
@RequestMapping("/api/auth")
@Api(value="Authentification")
public class AuthController {

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	UserRepository repoUser;
	
	@Autowired
	RoleRepository repoRole;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@PostMapping("signin")
	@ApiOperation(value="Authentification, retourne un token signé", response = String.class)
	@ApiResponses(value= {
			@ApiResponse(code=404, message = "Erreur login/mot de passe")
	})
	public ResponseEntity<?> signin(@Valid @RequestBody SigninRequest data){
		
		Authentication auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String jwt = tokenProvider.generateToken(auth);
		
		return ResponseEntity.ok(new SigninResponse(jwt));
				
	}
	
	@GetMapping("signout")
	@ApiOperation(value="Déconnecte l'utilisateur")
	public ResponseEntity<?> signout(@CurrentUser User currentUser){
		return null;
	}
	
}
