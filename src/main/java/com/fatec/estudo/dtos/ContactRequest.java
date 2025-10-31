package com.fatec.estudo.dtos;

public record ContactRequest(
    String name,
    String surname,
    Integer phone,
    String email,
    String nickname,
    String note
) {

}
