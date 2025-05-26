package br.start.petshop.DTOs;

import br.start.petshop.entities.Clients;
import br.start.petshop.enums.GenderEnum;
import br.start.petshop.enums.ServiceEnum;
import br.start.petshop.enums.SizeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;


import java.util.Date;


public record PetDTO(
         Long id,

         @NotNull(message = "name field is mandatory")
         String name,

         @NotNull(message = "birthDate field is mandatory")
         @JsonFormat(pattern = "dd-MM-yyyy")
         Date birthDate,

         @NotNull(message = "gender field is mandatory")
         GenderEnum gender,

         @NotNull(message = "species field is mandatory")
         String species,

         @NotNull(message = "breed field is mandatory")
         String breed,

         @NotNull(message = "size field is mandatory")
         SizeEnum size,

         @NotNull(message = "serviceType field is mandatory")
         ServiceEnum serviceType,

         @NotNull(message = "clientId field is mandatory")
         Long clientId
) {
}
