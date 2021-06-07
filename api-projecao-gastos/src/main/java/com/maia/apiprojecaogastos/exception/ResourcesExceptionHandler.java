package com.maia.apiprojecaogastos.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ResourcesExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse>handlerNotFoundException(Exception ex, HttpServletRequest request){
		ExceptionResponse error = ExceptionResponse.builder()
				.message("Recurso não encontrado")
				.path(request.getRequestURI())
				.details(ex.getMessage())
				.timestamp(new Date(System.currentTimeMillis()))
				.build();
		return ResponseEntity.status( HttpStatus.NOT_FOUND).body(error );
	}



}
