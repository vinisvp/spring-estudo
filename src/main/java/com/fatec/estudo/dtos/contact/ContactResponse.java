package com.fatec.estudo.dtos.contact;

import com.fatec.estudo.dtos.category.CategoryResponse;

// Records são objetos apenas de leitura
// Ou seja, não é possível mudar o valor dos atributos
// Só ler eles
public record ContactResponse(
    Long id,
    String name,
    String surname,
    Integer phone,
    String email,
    String nickname,
    String note,
    // Objeto da categoria desse contato
    CategoryResponse category
) {

}
