package com.fatec.estudo.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

// Isso vai fazer essa classe acompanhar as requisições feitas
// nos endpoints dos controllers
@ControllerAdvice
public class ResourceExceptionHandler {
    // Com essa anotação, poderemos tratar a exceção jogada em uma requisição.
    // precisamos especificar que exceção estamos tratando, com o seu nome e
    // .class no final
    @ExceptionHandler(EntityNotFoundException.class)
    // ResponseEntity para costumizar a resposta, StandarError para passar as infos.
    public ResponseEntity<StandardError> entityNotFoundException(
        // Pegando a exceção jogada
        EntityNotFoundException exception,
        // Pegando a requisição onde essa requisição foi jogada
        HttpServletRequest request
    ){
        // Instanciando o StandardError
        StandardError error = new StandardError();
        
        // Pegando o status code correto com o enum HttpStatus
        HttpStatus status = HttpStatus.NOT_FOUND;

        // Definindo o tipo de erro
        error.setError("Resource not found");
        // Definindo a mensagem usando a exceção jogada
        error.setMessage(exception.getMessage());
        // Definindo qual foi o endpoint da requisição
        error.setPath(request.getRequestURI());
        // Definindo o Status Code baseado no enum HttpStatus
        error.setStatus(status.value());
        // Definindo o instante em que o erro ocorreu
        error.setTimeStamp(Instant.now());

        // Retornando a resposta com o Staus Code correto
        // e mais o corpo contendo o StandardError
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValidException(
        MethodArgumentNotValidException exception,
        HttpServletRequest request
    ){
        ValidationError error = new ValidationError();

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        error.setError("Validation error");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());
        error.setTimeStamp(Instant.now());

        // Fazendo isso para pegar todos os erros de validação
        // e colocar a mensagem deles na lista do ValidationError
        exception.getBindingResult()
                 .getFieldErrors()
                 .forEach(e -> error.addError(e.getDefaultMessage()));

        return ResponseEntity.status(status).body(error);
    }
}
