package com.atlantic.turnstiles.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlantic.turnstiles.config.security.dtos.LoginResponseDto;
import com.atlantic.turnstiles.config.security.dtos.LoginUserDto;
import com.atlantic.turnstiles.config.security.dtos.RegisterUserDto;

import jakarta.validation.Valid;

@RequestMapping("api/auth")
@RestController
public class AuthenticationController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("signup")
	public ResponseEntity<LoginResponseDto> regiester(@RequestBody @Valid RegisterUserDto form ){
		var newUser = authenticationService.sigup(form);
		
		String jwtToken = jwtService.generateToken(newUser);
        var loginResponse = new LoginResponseDto(jwtToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
		
	}
	
	@PostMapping("login")
	public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto form){
		User authUser = authenticationService.authenticate(form);
		String jwtToken = jwtService.generateToken(authUser);
		
		var loginResponse  =  new LoginResponseDto(jwtToken);
		return ResponseEntity.ok(loginResponse);
	}
}
