package com.example.colegio.generic;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus errorCode = HttpStatus.NOT_FOUND;

	public NotFoundException() {
		super("Resource not found");
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}