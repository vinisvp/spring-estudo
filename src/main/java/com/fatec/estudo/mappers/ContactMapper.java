package com.fatec.estudo.mappers;

import com.fatec.estudo.dtos.contact.ContactRequest;
import com.fatec.estudo.dtos.contact.ContactResponse;
import com.fatec.estudo.entities.Contact;

public class ContactMapper {
    public static Contact toEntity(ContactRequest request){
        Contact contact = new Contact();

        contact.setName(request.name());
        contact.setSurname(request.surname());
        contact.setPhone(request.phone());
        contact.setEmail(request.email());
        contact.setNickname(request.nickname());
        contact.setNote(request.note());

        return contact;
    }

    public static ContactResponse toDto(Contact entity){
        return new ContactResponse(
            entity.getId(),
            entity.getName(),
            entity.getSurname(),
            entity.getPhone(),
            entity.getEmail(),
            entity.getNickname(),
            entity.getNote(),
            entity.getCategory() != null ? CategoryMapper.toDto(entity.getCategory()) : null
        );
    }
}
