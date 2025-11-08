package com.fatec.estudo.controllers.exceptions;

import java.time.Instant;

// Corpo de resposta padrão dos erros
public class StandardError {
    // Data e hora em que o erro ocorreu
    private Instant timeStamp;
    // Status Code do erro
    private Integer status;
    // "Titulo" do erro
    private String error;
    // Mensagem, ou seja, uma descrição do erro
    private String message;
    // endpoint em que o erro ocorreu
    private String path;
    
    // Getter e Setters
    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
}
