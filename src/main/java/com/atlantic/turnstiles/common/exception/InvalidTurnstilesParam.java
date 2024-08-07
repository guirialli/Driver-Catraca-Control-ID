package com.atlantic.turnstiles.common.exception;

public class InvalidTurnstilesParam extends RuntimeException{
	private static final long serialVersionUID = 1;
	
	public InvalidTurnstilesParam(String msg) {
		super(msg);
	}

}
