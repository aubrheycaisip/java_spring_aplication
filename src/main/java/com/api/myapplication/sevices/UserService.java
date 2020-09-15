package com.api.myapplication.sevices;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.myapplication.emuns.RoleName;
import com.api.myapplication.entity.User;
import com.api.myapplication.repository.RoleRepository;
import com.api.myapplication.repository.UserRepository;
import com.api.myapplication.request.UserSignupRequest;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public User save(UserSignupRequest request) {
		User user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
				.username(request.getUsername()).password(request.getPassword())
				.roles(Arrays.asList(roleRepository.findByAuthority(RoleName.valueOf(request.getRoles())))).build();
		return userRepository.save(user);

	}
}
