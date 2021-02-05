package com.maia.apiveiculos.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResourcesExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse>handlerNotFoundException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
										new Date(System.currentTimeMillis()),
										ex.getMessage(),
										request.getDescription(false));				
					
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceValidationException.class)
	public final ResponseEntity<ExceptionResponse>handlerValidationException(Exception ex, WebRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse(
										new Date(System.currentTimeMillis()),
										ex.getMessage(),
										request.getDescription(false)												);				
					
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}
