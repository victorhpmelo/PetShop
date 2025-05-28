package br.start.petshop.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record ClientDTO(
        Long id,

        @NotBlank(message = "Name is mandatory")
        @NotNull
        String name,

        @Email(message = "Invalid email format")
        @NotNull
        String email,

        @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
        @NotNull
        String phone,

        List<Long> petIds,

        List<Long> appointmentIds
) {}
