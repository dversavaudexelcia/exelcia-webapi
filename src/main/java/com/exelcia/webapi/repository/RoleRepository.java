package com.exelcia.webapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exelcia.webapi.model.Role;
import com.exelcia.webapi.model.RoleName;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findRoleByName(RoleName name);

}
