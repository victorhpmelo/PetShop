package br.start.petshop.services;

import br.start.petshop.DTOs.PetDTO;
import br.start.petshop.entities.Pet;
import br.start.petshop.exceptions.NotFoundException;
import br.start.petshop.exceptions.NullException;
import br.start.petshop.repositories.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if(pet != null) {
        return repo.save(pet);
        }
        else throw new NullException("Pet is null");
    }

    public Pet update(Long id, PetDTO dto) {
        Optional<Pet> optionalPet = repo.findById(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            pet.setName(dto.name());
            pet.setBirthDate(dto.birthDate());
            pet.setGender(dto.gender());
            pet.setSpecies(dto.species());
            pet.setBreed(dto.breed());
            pet.setSize(dto.size());
            pet.setServiceType(dto.serviceType());

            return repo.save(pet);
        } else {
        throw new NotFoundException("Pet not found with id: " + id);
        }
    }

    public void delete(final Long id) {

        Optional<Pet> pet = repo.findById(id);
        if (pet.isPresent()) {

        repo.deleteById(id);
        }
        else {
            throw new NotFoundException("Pet not found with id: " + id);
        }
    }
}
