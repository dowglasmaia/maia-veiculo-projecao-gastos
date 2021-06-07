package com.maia.apiprojecaogastos.exception;

import feign.FeignException;


public class ResourceNotFoundException extends FeignException {
	private static final long serialVersionUID = 6160126156716848995L;

	public ResourceNotFoundException(int status, String message) {
		super(status, message);
	}

}
