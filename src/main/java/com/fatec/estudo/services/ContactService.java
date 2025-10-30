package com.fatec.estudo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.estudo.entities.Contact;
import com.fatec.estudo.repositories.ContactRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(long id){
        return contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public Contact saveContact(Contact contact){
        return contactRepository.save(contact);
    }

    public void updateContact(Contact contact, long id){
        Contact aux = contactRepository.getReferenceById(id);
        
        aux.setName(contact.getName());
        aux.setSurname(contact.getSurname());
        aux.setPhone(contact.getPhone());
        aux.setEmail(contact.getEmail());
        aux.setNickname(contact.getNickname());
        aux.setNote(contact.getNote());

        contactRepository.save(aux);
    }

    public void deleteContact(long id){
        if(contactRepository.existsById(id)){
            contactRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
