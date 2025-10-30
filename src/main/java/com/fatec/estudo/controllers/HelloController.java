package com.fatec.estudo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Aqui estamos dizendo que isso será um Controller de Endpoints
@RestController
public class HelloController {
    // Aqui estamos dizendo que será um endpoint de metodo GET
    // E que seu endpoint será http://localhost:8080/hello
    @GetMapping("hello")
    public String helloWorld() {
        return "Hello World";
    }
}
