package com.fatec.estudo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContactRequest(
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    String name,
    String surname,
    @NotNull(message = "Phone is required")
    Integer phone,
    String email,
    String nickname,
    String note
) {

}
