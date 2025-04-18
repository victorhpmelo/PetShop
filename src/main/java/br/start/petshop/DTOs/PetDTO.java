package br.start.petshop.DTOs;

import br.start.petshop.enums.GenderEnum;
import br.start.petshop.enums.ServiceEnum;
import br.start.petshop.enums.SizeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


public record PetDTO(
         Long id,

         @NotNull
         String name,

         @NotNull
         @JsonFormat(pattern = "dd-MM-yyyy")
         Date birthDate,

         @NotNull
         GenderEnum gender,

         @NotNull
         String species,

         @NotNull
         String breed,

         @NotNull
         SizeEnum size,

         @NotNull
         ServiceEnum serviceType
) {
}
