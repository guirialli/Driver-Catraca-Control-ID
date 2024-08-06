package com.atlantic.turnstiles.common.exception;

public class PermissionDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PermissionDeniedException(String msg) {
		super(msg);
	}

}
