package com.fatec.estudo.mappers;

import java.util.stream.Collectors;

import com.fatec.estudo.dtos.contact.ContactRequest;
import com.fatec.estudo.dtos.contact.ContactResponse;
import com.fatec.estudo.entities.Contact;

public class ContactMapper {
    // static permite utilizar essa função sem instanciar essa classe
    // Ele vai converter um DTO request que ele vai receber para uma entidade
    public static Contact toEntity(ContactRequest request){
        // A entidade que será retornada
        Contact contact = new Contact();

        // Mudamos os valores da entidade com os valores do DTO
        // utilizando os getters e setters
        contact.setName(request.name());
        contact.setSurname(request.surname());
        contact.setPhone(request.phone());
        contact.setEmail(request.email());
        contact.setNickname(request.nickname());
        contact.setNote(request.note());

        // Retorna a entidade convertida
        return contact;
    }

    // Esse vai converter uma entidade que ele vai receber para um DTO de Response
    public static ContactResponse toDto(Contact entity){
        // Será instanciado um novo DTO, com os dados da entidade, e então retornar
        return new ContactResponse(
            entity.getId(),
            entity.getName(),
            entity.getSurname(),
            entity.getPhone(),
            entity.getEmail(),
            entity.getNickname(),
            entity.getNote(),
            // Se tiver categoria, ela será convertida para DTO
            entity.getCategory() != null ? CategoryMapper.toDto(entity.getCategory()) : null,
            entity.getTags().stream()
                .map(TagMapper::toDto)
                .collect(Collectors.toSet())
        );
    }
}
