package br.start.petshop.DTOs;

import br.start.petshop.enums.GenderEnum;
import br.start.petshop.enums.ServiceEnum;
import br.start.petshop.enums.SizeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public record PetDTO(
         Long id,

         String name,

         @JsonFormat(pattern = "dd-MM-yyyy")
         Date birthDate,

         GenderEnum gender,

         String species,

         String breed,

         SizeEnum size,

         ServiceEnum serviceType
) {
}
