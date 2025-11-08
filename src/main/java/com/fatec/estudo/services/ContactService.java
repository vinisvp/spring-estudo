package com.fatec.estudo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.estudo.dtos.contact.ContactRequest;
import com.fatec.estudo.dtos.contact.ContactResponse;
import com.fatec.estudo.entities.Category;
import com.fatec.estudo.entities.Contact;
import com.fatec.estudo.entities.Tag;
import com.fatec.estudo.mappers.ContactMapper;
import com.fatec.estudo.repositories.CategoryRepository;
import com.fatec.estudo.repositories.ContactRepository;
import com.fatec.estudo.repositories.TagRepository;

import jakarta.persistence.EntityNotFoundException;

// Aqui dizemos para o spring que essa classe é um serviço 
@Service
public class ContactService {
    // Aqui injetamos o Repositorio automaticamente na classe
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    // Aqui é a função para consultar todos os contatos no BD
    // Vamos retornar response para o cliente
    public List<ContactResponse> getContacts() {
        // A função findAll do JPA consulta todas as linhas da tabela
        return contactRepository.findAll()
                                // isso converte a lista para um stream
                                // que permite usar o map
                                .stream()
                                // map() vai realizar uma função com cada item
                                // e criar uma nova coleção com os retornos
                                // ou seja, vamos converter cada Contact para
                                // Response, e criar uma nova lista com isso
                                .map(ContactMapper::toDto)
                                // E então, converte o stream de volta para uma lista
                                .toList();
    }

    // Aqui é a função para consultar por id.
    // Ele vai rebecer um número, para consultar o contato com esse numero de id 
    public ContactResponse getContactById(long id){
        // findById permite a gente encontrar uma linha na tabela pelo id
        return contactRepository.findById(id)
                // Vai converto o Contact para Response
                .map(ContactMapper::toDto)
                // Essa função necessita do orElseThrow, uma função que joga
                // uma exceção (erro) caso a linha com aquele id não seja encontrada
                .orElseThrow(() -> new EntityNotFoundException("Contact not found!"));
    }

    // Aqui é a função que vai salvar um contato
    // Ele vai receber um objeto ContactRequest, que é o que
    // o cliente manda para a API
    public ContactResponse saveContact(ContactRequest contact){
        Contact entity = ContactMapper.toEntity(contact);

        // Se o cliente estiver tentando relacionar uma categoria
        if(contact.categoryId() != null){
            // Pegamos a categoria pela tabela no bd
            Category category = categoryRepository.findById(contact.categoryId())
                                // Se não existir, joga exceção
                                .orElseThrow(() -> new EntityNotFoundException("Category not found!"));

            // Associação a categoria na entidade
            entity.setCategory(category);
        }

        if (contact.tagIds() != null && !contact.tagIds().isEmpty()) {
            Set<Tag> tags = new HashSet<>();
            for (Long tagId : contact.tagIds()) {
                Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new EntityNotFoundException("Tag not found: " + tagId));
                tags.add(tag);
            }
            entity.setTags(tags);
        }

        entity = contactRepository.save(entity);
        // save() salva um contato, através de uma entidade Contact
        return ContactMapper.toDto(entity);
    }

    // Aqui é a função que vai editar um contato.
    // Ele receber uma Instacia Contact, e vai utilizar seu valores para
    // atualizar o que está na tabela, e o id do contact a ser editado
    public void updateContact(ContactRequest contact, long id){
        // Usamos o getReferenceById(), para pegar o contato do BD
        Contact aux = contactRepository.getReferenceById(id);
        
        // aqui, estamos mudando os valores do item presente (aux) no BD
        // pelos valores do item com as atualizações (contact) utilizando
        // os setters da entidade, e os campos do DTO, que é um record
        aux.setName(contact.name());
        aux.setSurname(contact.surname());
        aux.setPhone(contact.phone());
        aux.setEmail(contact.email());
        aux.setNickname(contact.nickname());
        aux.setNote(contact.note());

        if(contact.categoryId() != null){
            Category category = categoryRepository.findById(contact.categoryId())
                                .orElseThrow(() -> new EntityNotFoundException("Category not found!"));

            aux.setCategory(category);
        } else {
            // Caso não tenha id, o cliente pode estar querendo tirar o relacionamento
            aux.setCategory(null);
        }

        if (contact.tagIds() != null) {
            Set<Tag> tags = new HashSet<>();
            for (Long tagId : contact.tagIds()) {
                Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new EntityNotFoundException("Tag not found: " + tagId));
                tags.add(tag);
            }
            aux.setTags(tags);
        } else {
            aux.getTags().clear();
        }

        // Usamos o save() para atualizar o registro no BD
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
            throw new EntityNotFoundException("Contact not found!");
        }
    }
}
