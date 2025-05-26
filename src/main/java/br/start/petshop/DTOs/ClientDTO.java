package br.start.petshop.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientDTO(
        Long id,

        @NotBlank(message = "Name is mandatory")
        String name,

        @Email(message = "Invalid email format")
        String email,

        @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
        String phone
) {}
