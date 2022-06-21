package com.spring.project.handiling;

import java.util.Date;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.project.error.ErrorResponse;
import com.spring.project.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class EmployeeNotFoundHandiling {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> noEmployeeFoundHandler(EmployeeNotFoundException enfe){
		return new ResponseEntity<ErrorResponse>(ErrorResponse
											.builder()
											.module("Employee Module")
											.message(enfe.getMessage())
											.status(HttpStatus.BAD_REQUEST)
											.date(new Date().toString())
											.build()
											,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> contraintViolate(ConstraintViolationException cve){
		return new ResponseEntity<ErrorResponse>(ErrorResponse
				.builder()
				.module("Employee Module")
				.message(cve.getMessage())
				.status(HttpStatus.BAD_REQUEST)
				.date(new Date().toString())
				.build(),HttpStatus.BAD_REQUEST);
	}
}
