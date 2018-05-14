package org.vermeg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.vermeg.entities.AppRole;
import org.vermeg.entities.AppUser;
import org.vermeg.repository.RoleRepository;
import org.vermeg.repository.UserRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Override
	public AppUser saveUser(AppUser user) {
		String hashpw = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashpw);
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepository.save(role);
	}

	@Override
	public void AddRoleToUse(String username, String rolename) {
		AppRole role = roleRepository.findByRoleName(rolename);
		AppUser user = userRepository.findByUsername(username);
		user.getRoles().add(role);
	}


	@Override
	public AppUser findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
