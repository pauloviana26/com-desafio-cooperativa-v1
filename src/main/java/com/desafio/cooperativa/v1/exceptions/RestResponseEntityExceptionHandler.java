package com.desafio.cooperativa.v1.exceptions;

import com.desafio.cooperativa.v1.dtos.ErroDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handle(RuntimeException ex, WebRequest request) {
        var erro = new ErroDTO();
        if(Objects.nonNull(ex.getCause())) {
            erro.setMensagem(ex.getCause().getMessage());
        } else {
            erro.setMensagem(ex.getMessage());
        }
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getCause() instanceof BusinessException businessException) {
            httpStatus = businessException.getHttpStatus();
        }
        return handleExceptionInternal(ex, erro, new HttpHeaders(), httpStatus, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var erro = new ErroDTO();
        erro.setMensagem("Campo(s) inválido(s).");
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> erro.adicionarErros(fieldError.getField(), fieldError.getDefaultMessage()));
        return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
