package com.abn.amro.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.abn.amro.entity.ErrorResponse;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoRecordFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleNoRecordFoundException(NoRecordFoundException ex) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("No Records Found");
		errorResponse.setStatus(HttpStatus.NOT_FOUND.toString());
		return errorResponse;
	}

	@ExceptionHandler(BadRequestFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleBadRequestFoundException(BadRequestFoundException ex) {

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("Received Invalid Input");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
		return errorResponse;
	}
}
