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
@RequestMapping("contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Contact>> getContacts() {
        return ResponseEntity.ok(contactService.getContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable long id){
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
