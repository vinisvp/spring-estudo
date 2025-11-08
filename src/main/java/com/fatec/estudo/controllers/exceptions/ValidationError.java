package com.fatec.estudo.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

// Corpo de resposta dos erros de validação
public class ValidationError extends StandardError {
    // Uma lista contendo todos os erros que validação que ocorreram
    private List<String> errors = new ArrayList<>();

    // Adicionar a mensagem de um erro de validação na lista
    public void addError(String error){
        this.errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }
}
