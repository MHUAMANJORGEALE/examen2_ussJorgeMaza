package com.facturacion.almacenamiento2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class NoDataServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NoDataServiceException() {
		super();
	}

	public NoDataServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoDataServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoDataServiceException(String message) {
		super(message);
	}

	public NoDataServiceException(Throwable cause) {
		super(cause);
	}
	
}
