package com.fatec.estudo.dtos.tag;

import jakarta.validation.constraints.NotBlank;

public record TagRequest(
    @NotBlank(message = "Tag name is required!")
    String name
) {

}
