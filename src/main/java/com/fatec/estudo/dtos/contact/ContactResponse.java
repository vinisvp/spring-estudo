package com.fatec.estudo.dtos.contact;

import java.util.Set;

import com.fatec.estudo.dtos.category.CategoryResponse;
import com.fatec.estudo.dtos.tag.TagResponse;

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
    CategoryResponse category,
    // Lista dos objetos das tags desse contato
    Set<TagResponse> tags
) {

}
