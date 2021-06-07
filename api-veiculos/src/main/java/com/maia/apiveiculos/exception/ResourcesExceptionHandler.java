package com.maia.apiveiculos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ResourcesExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse>handlerNotFoundException(Exception ex, HttpServletRequest request){
		ExceptionResponse notFoundExceptionResponse = ExceptionResponse.builder()
				.message("Recurso não encontrado")
				.path(request.getRequestURI())
				.details(ex.getMessage())
				.timestamp(new Date(System.currentTimeMillis()))
				.build();
		return ResponseEntity.status( HttpStatus.NOT_FOUND).body(notFoundExceptionResponse );
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		BindingResult bindingResult = e.getBindingResult();
		ValidationFieldError erros = new ValidationFieldError(
				new Date(System.currentTimeMillis()),
				"Erro de Validação",
				e.getMessage(),
				request.getRequestURI()
		);
		e.getBindingResult().getFieldErrors().forEach( err -> {
			erros.addError(err.getField(),err.getDefaultMessage());
		});
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erros);
	}

}
