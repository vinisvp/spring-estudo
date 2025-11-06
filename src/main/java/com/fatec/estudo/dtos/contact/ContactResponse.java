package com.fatec.estudo.dtos.contact;

import com.fatec.estudo.dtos.category.CategoryResponse;

public record ContactResponse(
    Long id,
    String name,
    String surname,
    Integer phone,
    String email,
    String nickname,
    String note,
    CategoryResponse category
) {

}
