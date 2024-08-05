package com.atlantic.turnstiles.config.handlers;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atlantic.turnstiles.common.http.ResponseError;

@ControllerAdvice
public class GlobalHandlerException {
	private ResponseEntity<ResponseError> mountResponse(int status, String message) {
		var err = new ResponseError(status, message);
		return ResponseEntity.status(status).body(err);	
	}
	private ResponseEntity<ResponseError> mountResponse(int status, String message, String ex) {
		System.out.println(ex);
		return this.mountResponse(status, message);
	}
	

	@ExceptionHandler(URISyntaxException.class)
	public ResponseEntity<ResponseError> handlerURISyntaxException(URISyntaxException ex){
		return this.mountResponse(500, "Internal Error!", ex.getMessage());

	}
	
	@ExceptionHandler(InterruptedException.class)
	public ResponseEntity<ResponseError> handleInterruptedException(InterruptedException ex){
		return this.mountResponse(500, "Internal Error", ex.getMessage());
	}
	
}
