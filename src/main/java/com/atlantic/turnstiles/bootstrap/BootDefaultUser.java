package com.atlantic.turnstiles.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.atlantic.turnstiles.config.security.AuthenticationService;
import com.atlantic.turnstiles.config.security.UserRepository;
import com.atlantic.turnstiles.config.security.dtos.RegisterUserDto;

@Component
public class BootDefaultUser implements ApplicationRunner{
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthenticationService service;
	
	@Value("${security.user}")
	private String username;
	
	@Value("${security.password}")
	private String password;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		var user = repository.findByUsername(username);
		
		if(user.isEmpty()) {
			var form = new RegisterUserDto(username, password);
			service.sigup(form);
		}
		
	}

}
