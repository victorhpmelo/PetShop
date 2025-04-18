package br.start.petshop.DTOs;

import br.start.petshop.enums.SizeEnum;

public record PetDTO(
         String name,
         Long age,
         String gender,
         String species,
         SizeEnum size
) {
}
