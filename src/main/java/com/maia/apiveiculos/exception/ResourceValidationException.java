package com.maia.apiveiculos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceValidationException extends RuntimeException {
	private static final long serialVersionUID = 6160126156716848995L;

	public ResourceValidationException(String exception) {
	super(exception);
	}

}
