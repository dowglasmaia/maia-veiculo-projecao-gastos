package com.maia.apiveiculos.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.*;


@AllArgsConstructor
@Getter
@Builder
public class ExceptionResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Date timestamp;
	private String message;
	private String details;
	private String path;

}
