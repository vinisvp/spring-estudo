package com.fatec.estudo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.estudo.entities.Contact;

// Declarando o Repositório de Contact
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
