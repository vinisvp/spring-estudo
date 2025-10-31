package com.fatec.estudo.dtos;

public record ContactResponse(
    Long id,
    String name,
    String surname,
    Integer phone,
    String email,
    String nickname,
    String note
) {

}
