package com.fatec.estudo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.estudo.dtos.ContactRequest;
import com.fatec.estudo.dtos.ContactResponse;
import com.fatec.estudo.services.ContactService;

import java.net.URI;
import java.util.List;

@RestController
// Aqui estamos dizendo que todos os endpoinst começaram com 'contacts', 
// ou seja: http://localhost:8080/contacts
@RequestMapping("contacts")
public class ContactController {
    // Injetando o Serviço
    @Autowired
    private ContactService contactService;

    // Aqui estamos dizendo que essa função será um endpoint de GET
    @GetMapping
    // ResponseEntity é necessário para dar respostas mais completas 
    // Precisamos especificar o que vai ser retornado no Endpoint
    public ResponseEntity<List<ContactResponse>> getContacts() {
        // ok() é o Status Code 200, dizendo que a função deu certo. 
        // dentro dela, colocamos o que vamos retornar, os contatos
        return ResponseEntity.ok(contactService.getContacts());
    }

    // Aqui estamos dizendo que essa função será GET, mas terá /{id} no final,
    // ou seja http://localhost:8080/contacts/{id}. Esse {id} será substituido
    @GetMapping("/{id}")
    // Aqui só sera retornado uma instancia de Contact
    // @PathVariable vai pegar o valor que vai substituir {id}
    public ResponseEntity<ContactResponse> getContactById(@PathVariable long id){
        // Agora usamos ok, mas com a função de getById, e passamos o id
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    // Aqui estamos indicando que essa função será um endpoint de POST
    @PostMapping
    // @RequestBody faz com que pegamos o corpo da requisição, que é um objeto Contact
    public ResponseEntity<ContactResponse> saveContact(@RequestBody ContactRequest contact){
        // Pegamos o contato retornado ao salvar
        ContactResponse savedContact = contactService.saveContact(contact);

        // Aqui criamos o endpoint onde pode ser encontrado o novo Contact
        URI location = ServletUriComponentsBuilder //Isso criar uma URI
                        // Pega o contexto atual, ou seja http://localhost:8080/contacts
                        .fromCurrentRequest()
                        // adiciona /{id} no final, ou seja: http://localhost:8080/contacts/{id}
                        .path("/{id}")
                        // substitui o {id} pelo o id do contato salvo
                        .buildAndExpand(savedContact.id())
                        // Converte tudo em uma URI
                        .toUri();

        // created() retorna o Status Code 201, indicando que o item foi criado
        // ele precisa do local onde pode ser encontrado o item (location)
        // e o item que foi salvo (body)
        return ResponseEntity.created(location).body(savedContact);
    }

    // Aqui estamos indicando que essa função é um endpoint de PUT
    // que vai possuir /{id} no final: http://localhost:8080/contacts/{id}
    @PutMapping("/{id}")
    // Pegamos o id com @PathVariable, e o @RequestBody para pegar o corpo da requisição
    public ResponseEntity<Void> updateContact(@PathVariable long id, @RequestBody ContactRequest contact){
        // Executamos a função do serviço, passando o contact e o id
        contactService.updateContact(contact, id);
        // Usamos noContent(), por não ter nada a retornar
        return ResponseEntity.noContent().build();
    }

    // Aqui estamos indicando que essa função é um endpoint de DELETE
    // que vai possuir /{id} no final: http://localhost:8080/contacts/{id}
    @DeleteMapping("/{id}")
    // Usamos o @PathVariable para pegar o id
    public ResponseEntity<Void> deleteContactById(@PathVariable long id){
        // Usamos deleteContact do service para remover o contato
        contactService.deleteContact(id);
        // noContent() retorna o Status Code 204, indicando que a requisição
        // deu certo, mas não tem nada a retornar
        return ResponseEntity.noContent().build();
    }
}
