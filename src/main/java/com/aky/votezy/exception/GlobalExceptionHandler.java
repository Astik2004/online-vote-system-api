package com.aky.votezy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Global exception handler to handle custom exceptions and return appropriate HTTP responses.</br>
 * <b>Author : Er.Astik Yadav</b>
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	//ResourceNotFoundException handler
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	//DuplicateResourceException handler
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}
	
	//VoteNotAllowedException handler
	@ExceptionHandler(VoteNotAllowedException.class)
	public ResponseEntity<ErrorResponse> handleVoteNotAllowedException(VoteNotAllowedException ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}
	
	//
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred.");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
