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

import com.fatec.estudo.entities.Contact;
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
    public ResponseEntity<List<Contact>> getContacts() {
        // ok() é o Status Code 200, dizendo que a função deu certo. 
        // dentro dela, colocamos o que vamos retornar, os contatos
        return ResponseEntity.ok(contactService.getContacts());
    }

    // Aqui estamos dizendo que essa função será GET, mas terá /{id} no final,
    // ou seja http://localhost:8080/contacts/{id}. Esse {id} será substituido
    @GetMapping("/{id}")
    // Aqui só sera retornado uma instancia de Contact
    // @PathVariable vai pegar o valor que vai substituir {id}
    public ResponseEntity<Contact> getContactById(@PathVariable long id){
        // Agora usamos ok, mas com a função de getById, e passamos o id
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @PostMapping
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact){
        Contact savedContact = contactService.saveContact(contact);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedContact.getId())
                        .toUri();

        return ResponseEntity.created(location).body(savedContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateContact(@PathVariable long id, @RequestBody Contact contact){
        contactService.updateContact(contact, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactById(@PathVariable long id){
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
