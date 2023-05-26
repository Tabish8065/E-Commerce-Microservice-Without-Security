package com.gateway.api.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler{

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception{
		Map<String,Object> map = new LinkedHashMap<>();
		map.put("TimeStamp",LocalDateTime.now());
		map.put("message" , ex.getMessage());
		
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}

}
