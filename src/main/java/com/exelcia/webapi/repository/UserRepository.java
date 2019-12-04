package com.exelcia.webapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exelcia.webapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findUserByUsername(String username);

}
