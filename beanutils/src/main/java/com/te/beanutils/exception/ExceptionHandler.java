package com.te.beanutils.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.beanutils.response.AppResponse;

@RestControllerAdvice
public class ExceptionHandler {
	@Autowired
	private com.te.beanutils.response.AppResponse appResponse;

	@org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotPresentException.class)
	public ResponseEntity<AppResponse> EmployoyeeNotPresentException(EmployeeNotPresentException ex) {
		appResponse.setError(false);
		appResponse.setMessage(ex.getMessage());
		appResponse.setStatus(400);
		return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);

	}
}
