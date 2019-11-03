package com.fonyou.finaltest.controller;

import javax.persistence.NoResultException;

import org.hibernate.exception.JDBCConnectionException;
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
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	/**
	 * Handle IllegalArgumentException.
	 *
	 * @param IllegalArgumentException the exception
	 * @return the message of the error
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseGenericDto<String>> handleException(IllegalArgumentException exception)
	{
		System.out.println("It was an illegal, sorry");
		LOGGER.error(exception.getMessage(), exception);
		ResponseGenericDto<String> body = new ResponseGenericDto<String>("1", "Failed validation", exception.getMessage(), false);
		return new ResponseEntity<ResponseGenericDto<String>>(body, HttpStatus.PRECONDITION_REQUIRED);
	}
	
	/**
	 * Handle NoResultException.
	 *
	 * @param NoResultException the exception
	 * @return the message of the error
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseGenericDto<String>> handleException(NoResultException exception)
	{
		System.out.println("Doesn't have something, sorry");
		LOGGER.error(exception.getMessage(), exception);
		ResponseGenericDto<String> body = new ResponseGenericDto<String>("1", "Failed content", exception.getMessage(), false);
		return new ResponseEntity<ResponseGenericDto<String>>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseGenericDto<String>> handleException(JDBCConnectionException exception)
	{
		LOGGER.error(exception.getMessage(), exception);
		ResponseGenericDto<String> body = new ResponseGenericDto<String>("1", "Failed content", "Error with the database", false);
		return new ResponseEntity<ResponseGenericDto<String>>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseGenericDto<String>> handleException(NullPointerException exception)
	{
		LOGGER.error(exception.getMessage(), exception);
		ResponseGenericDto<String> body = new ResponseGenericDto<String>("1", "Failed content", "Error with the database", false);
		return new ResponseEntity<ResponseGenericDto<String>>(body, HttpStatus.NOT_FOUND); 
	}
	
	/**
	 * Handle Exception.
	 *
	 * @param Exception the exception
	 * @return the message of the error
	 */
	@ExceptionHandler
	public ResponseEntity<ResponseGenericDto<String>> handleException(Exception exception)
	{
		LOGGER.error(exception.getMessage(), exception);
		ResponseGenericDto<String> body = new ResponseGenericDto<String>("1", "Unhandled exception", exception.getLocalizedMessage(), false);
		System.out.println("\n\n===============> " + exception + "\n\n");
		return new ResponseEntity<ResponseGenericDto<String>>(body, HttpStatus.BAD_REQUEST);
	}
}
