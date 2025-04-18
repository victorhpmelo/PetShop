package br.start.petshop.services;

import br.start.petshop.DTOs.PetDTO;
import br.start.petshop.entities.Pet;
import br.start.petshop.repositories.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService {

    private PetRepository repo;


    public Pet toEntity(PetDTO dto) {
        Pet pet = new Pet();

        pet.setId(dto.id());
        pet.setName(dto.name());
        pet.setBirthDate(dto.birthDate());
        pet.setGender(dto.gender());
        pet.setSpecies(dto.species());
        pet.setBreed(dto.breed());
        pet.setSize(dto.size());
        pet.setServiceType(dto.serviceType());

        return pet;
    }


    public Pet save(PetDTO dto) {
        Pet pet = toEntity(dto);
        return repo.save(pet);
    }

    public Pet update(Long id, PetDTO dto) {
        Pet pet = repo.findById(id).orElse(null);
        if (pet != null) {
        pet = toEntity(dto);
        return repo.save(pet);
        }

        return null;
    }

    public void delete(final Long id) {
        repo.findById(id);
        repo.deleteById(id);
    }
}
