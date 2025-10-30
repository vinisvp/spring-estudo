package com.fatec.estudo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

// Aqui especificamos que essa classe será uma entidade na aplicação.
// O Hibernate, um ORM que vem junto com o JPA vai criar tabelas no BD
// se baseando nessa anotação.
@Entity
// Aqui podemos customizar a tabela no BD. Nesse caso, estamos renomeando ela
@Table(name = "TBL_CONTACT")
public class Contact {
    // Aqui estamos dizendo que esse atributo será o Id, ou seja, a chave primaria da tabela
    @Id
    // Aqui estamos dizendo que o valor do Id será gerado automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Aqui são os outros atributos da entidade
    // Importante que seus tipos sejam objetos.
    // Podemos observar isso com a primeira letra
    // maiuscula de String e Integer.
    private String name;
    private String surname;
    private Integer phone;
    private String email;
    private String nickname;
    private String note;
    
    // Método construtor
    public Contact() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
