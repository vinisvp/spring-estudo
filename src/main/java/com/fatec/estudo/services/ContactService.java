package com.fatec.estudo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.estudo.entities.Contact;
import com.fatec.estudo.repositories.ContactRepository;

import jakarta.persistence.EntityNotFoundException;

// Aqui dizemos para o spring que essa classe é um serviço 
@Service
public class ContactService {
    // Aqui injetamos o Repositorio automaticamente na classe
    @Autowired
    private ContactRepository contactRepository;

    // Aqui é a função para consultar todos os contatos no BD
    public List<Contact> getContacts() {
        // A função findAll do JPA consulta todas as linhas da tabela
        return contactRepository.findAll();
    }

    // Aqui é a função para consultar por id.
    // Ele vai rebecer um número, para consultar o contato com esse numero de id 
    public Contact getContactById(long id){
        // findById permite a gente encontrar uma linha na tabela pelo id
        return contactRepository.findById(id)
                // Essa função necessita do orElseThrow, uma função que joga
                // uma exceção (erro) caso a linha com aquele id não seja encontrada
                .orElseThrow(() -> new EntityNotFoundException());
    }

    // Aqui é a função que vai salvar um contato
    // Ele vai receber um objeto Contact
    public Contact saveContact(Contact contact){
        // save() salva um contato, através de uma entidade Contact
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

    // Aqui é a função que deletar um contato.
    // Ele vai receber o id do contato a ser deletado
    public void deleteContact(long id){
        // existById indentifica se esse contato existe pelo o id.
        // Se existe, true, senão, false
        if(contactRepository.existsById(id)){
            // deleteById remove o contato que possui esse id
            contactRepository.deleteById(id);
        } else {
            // Se não existe, joga uma exceção
            throw new EntityNotFoundException();
        }
    }
}
