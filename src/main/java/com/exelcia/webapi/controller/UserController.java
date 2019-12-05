package com.exelcia.webapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exelcia.webapi.repository.UserRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/user")
@Api(value="Utilisateurs")
public class UserController {
	
	//@Autowired
	//UserRepository repoUser;
	
	//@GetMapping("/active")
	//public ResponseEntity activeUser (@RequestParam String username) {
		//Boolean resultat =!repoUser
		
		
	//}

}
