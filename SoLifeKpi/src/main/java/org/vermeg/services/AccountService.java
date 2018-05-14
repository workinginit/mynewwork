package org.vermeg.services;

import org.vermeg.entities.AppRole;
import org.vermeg.entities.AppUser;

public interface AccountService {

	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void AddRoleToUse(String username, String rolename);
	public AppUser findUserByUsername(String username);
}
