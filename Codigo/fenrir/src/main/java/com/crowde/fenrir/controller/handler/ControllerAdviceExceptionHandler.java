package com.crowde.fenrir.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.crowde.fenrir.service.exception.DadosException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(DadosException.class)
	public ResponseEntity<String> handleDadosJaCadastradosException(DadosException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
