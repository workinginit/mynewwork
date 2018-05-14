package org.vermeg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vermeg.entities.AppUser;


public interface UserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String username);
}
