package com.fonyou.finaltest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fonyou.finaltest.dto.ResponseGenericDto;


/**
 * The Class ErrorController.
 */
@RestControllerAdvice
public class ErrorController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);
	/**
	 * Handle IllegalArgumentException.
	 *
	 * @param exception the exception
	 * @return the message of the error
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseGenericDto<String>> handleException(IllegalArgumentException exception)
	{
		LOGGER.error(exception.getMessage(), exception);
		ResponseGenericDto<String> body = new ResponseGenericDto<>("1", "Failed validation", exception.getMessage(), false);
		return new ResponseEntity<>(body, HttpStatus.PRECONDITION_REQUIRED);
	}

	
	@ExceptionHandler
	public ResponseEntity<ResponseGenericDto<String>> handleException(NullPointerException exception)
	{
		LOGGER.error(exception.getMessage(), exception);
		ResponseGenericDto<String> body = new ResponseGenericDto<>("1", "Failed content", "Error with the database", false);
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handle Exception.
	 *
	 * @param exception the exception
	 * @return the message of the error
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseGenericDto<String>> handleException(Exception exception)
	{
		LOGGER.error(exception.getMessage(), exception);
		ResponseGenericDto<String> body = new ResponseGenericDto<>("1", "Unhandled exception", exception.getLocalizedMessage(), false);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
