package com.atlantic.turnstiles.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.atlantic.turnstiles.common.exception.PermissionDeniedException;
import com.atlantic.turnstiles.config.security.dtos.LoginUserDto;
import com.atlantic.turnstiles.config.security.dtos.RegisterUserDto;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	public User sigup(RegisterUserDto form) {
		var user = new User(form.username());
		user.setPassword(passwordEncoder.encode(form.password()));
		userRepository.save(user);
		return user;
	}

	public User authenticate(LoginUserDto form) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.username(), form.password()));
		return userRepository.findByUsername(form.username())
				.orElseThrow(() -> new PermissionDeniedException("username or password invalid!"));
	}

}
