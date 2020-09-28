package com.kakao.task.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kakao.task.service.sprayService;

@RestControllerAdvice
public class ApiControllerAdvice {
	
	@Autowired
	private sprayService service;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest()
        		.contentType(MediaType.APPLICATION_JSON_UTF8)
        		.body(service.getErrorCode(ex.getBindingResult().getAllErrors()));
    }
}
