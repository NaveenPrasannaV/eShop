package com.cg.shopping.exceptioncontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.shopping.exception.AdminSignInRequiredException;
import com.cg.shopping.exception.EmailAlreadyExistException;
import com.cg.shopping.exception.IllegalValueException;
import com.cg.shopping.exception.NoProductFoundException;
import com.cg.shopping.exception.NoUserFoundException;
import com.cg.shopping.exception.NullValueFieldException;
import com.cg.shopping.exception.PasswordLengthMismatchException;
import com.cg.shopping.exception.PasswordMismatchException;
import com.cg.shopping.exception.PasswordWithNoDigitException;
import com.cg.shopping.exception.PasswordWithNoSplCharException;
import com.cg.shopping.exception.ProductAlreadyExistException;
import com.cg.shopping.exception.ProductNotAvailableException;
import com.cg.shopping.exception.UserSignInRequiredException;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> EmailAlreadyExistException(EmailAlreadyExistException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.ALREADY_REPORTED.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}

	@ExceptionHandler(NullValueFieldException.class)
	public ResponseEntity<ErrorMessage> NullValueFieldException(NullValueFieldException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}

	@ExceptionHandler(PasswordLengthMismatchException.class)
	public ResponseEntity<ErrorMessage> PasswordLengthMismatchException(PasswordLengthMismatchException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.LENGTH_REQUIRED.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}

	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<ErrorMessage> PasswordMismatchException(PasswordMismatchException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}

	@ExceptionHandler(PasswordWithNoDigitException.class)
	public ResponseEntity<ErrorMessage> PasswordWithNoDigitException(PasswordWithNoDigitException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}

	@ExceptionHandler(PasswordWithNoSplCharException.class)
	public ResponseEntity<ErrorMessage> PasswordWithNoSplCharException(PasswordWithNoSplCharException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}

	@ExceptionHandler(AdminSignInRequiredException.class)
	public ResponseEntity<ErrorMessage> AdminSignInRequiredException(AdminSignInRequiredException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}

	@ExceptionHandler(ProductNotAvailableException.class)
	public ResponseEntity<ErrorMessage> ProductNotAvailableException(ProductNotAvailableException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}

	@ExceptionHandler(ProductAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> ProductAlreadyExistException(ProductAlreadyExistException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.ALREADY_REPORTED.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(IllegalValueException.class)
	public ResponseEntity<ErrorMessage> IllegalValueException(IllegalValueException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(NoProductFoundException.class)
	public ResponseEntity<ErrorMessage> NoProductFoundException(NoProductFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(UserSignInRequiredException.class)
	public ResponseEntity<ErrorMessage> UserSignInRequiredException(UserSignInRequiredException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<ErrorMessage> NoUserFoundException(NoUserFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
