package com.atlantic.turnstiles.config.handlers;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atlantic.turnstiles.common.exception.CannotEditPropsException;
import com.atlantic.turnstiles.common.exception.InvalidTurnstilesParam;
import com.atlantic.turnstiles.common.exception.PermissionDeniedException;
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

	@ExceptionHandler(InvalidTurnstilesParam.class)
	public ResponseEntity<ResponseError> handleInvalidTurnstilesParam(InvalidTurnstilesParam ex) {
		return this.mountResponse(400, ex.getMessage());
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ResponseError> hanldeIOException(IOException ex) {
		return this.mountResponse(400, ex.getMessage());
	} 

	@ExceptionHandler(MethodArgumentNotValidException.class)
 	public ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return this.mountResponse(400, ex.getMessage());
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ResponseError> handleBadCredentialsException(BadCredentialsException ex) {
		return this.mountResponse(401, ex.getMessage());
	}

	@ExceptionHandler(PermissionDeniedException.class)
	public ResponseEntity<ResponseError> handlePermissionDeniedException(PermissionDeniedException ex) {
		return this.mountResponse(404, ex.getMessage());
	}

	@ExceptionHandler(URISyntaxException.class)
	public ResponseEntity<ResponseError> handlerURISyntaxException(URISyntaxException ex) {
		return this.mountResponse(500, "Internal Error!", ex.getMessage());

	}

	@ExceptionHandler(InterruptedException.class)
	public ResponseEntity<ResponseError> handleInterruptedException(InterruptedException ex) {
		return this.mountResponse(500, "Internal Error", ex.getMessage());
	}

	@ExceptionHandler(CannotEditPropsException.class)
	public ResponseEntity<ResponseError> handleCannotEditPropsException(CannotEditPropsException ex) {
		return this.mountResponse(500, ex.getMessage());
	}

}
