package org.vermeg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vermeg.entities.AppRole;


public interface RoleRepository extends JpaRepository<AppRole, Long>{

	public AppRole findByRoleName(String roleName);
}

