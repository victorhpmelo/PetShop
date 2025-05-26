package br.start.petshop.services;

import br.start.petshop.DTOs.ClientDTO;
import br.start.petshop.DTOs.PetDTO;
import br.start.petshop.entities.Clients;
import br.start.petshop.entities.Pet;
import br.start.petshop.exceptions.NotFoundException;
import br.start.petshop.exceptions.NullException;
import br.start.petshop.repositories.ClientRepository;
import br.start.petshop.repositories.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class PetService {

    private PetRepository repo;
    private ClientRepository clientRepo;

    public Pet toEntity(PetDTO dto) {
        log.info("Converting PetDTO to Pet entity: {}", dto.name());

            Pet pet = new Pet();

            Optional<Clients> client = clientRepo.findById(dto.clientId());
            pet.setName(dto.name());
            pet.setBirthDate(dto.birthDate());
            pet.setGender(dto.gender());
            pet.setSpecies(dto.species());
            pet.setBreed(dto.breed());
            pet.setSize(dto.size());
            pet.setServiceType(dto.serviceType());
            pet.setClient(client.get());

            return pet;
        }

    public List<Pet> getAll(){
        log.info("Fetching all pets");
        List<Pet> pets = repo.findAll();
        if (!pets.isEmpty()) {
            return pets;
        }
        else {
            log.warn("No pets found in the database");
            throw new NotFoundException("Pets not found");
        }
    }

    public Optional<Pet> getById(Long id) {
        log.info("Searching for pet with id: {}", id);
        return repo.findById(id).or(() -> {
            log.warn("Pet with id {} not found", id);
            throw new NotFoundException("Pet not found");
        });
    }

    public List<Pet> getBySpecies(String species){
        log.info("Fetching pets by species: {}", species);
        List<Pet> pets = repo.findBySpecies(species);
        if (!pets.isEmpty()) {
            return pets;
        } else {
            log.warn("No pets found with species: {}", species);
            throw new NotFoundException("Pet with this Species not found");
        }
    }

    public Pet save(PetDTO dto) {
        log.info("Saving new pet: {}", dto.name());
        Pet pet = toEntity(dto);
        if(pet != null) {
            return repo.save(pet);
        } else {
            log.error("PetDTO is null");
            throw new NullException("Pet is null");
        }
    }

    public Pet update(Long id, PetDTO dto) {
        log.info("Updating pet with id: {}", id);
        return repo.findById(id).map(pet -> {
            Optional<Clients> client = clientRepo.findById(dto.clientId());
            pet.setName(dto.name());
            pet.setBirthDate(dto.birthDate());
            pet.setGender(dto.gender());
            pet.setSpecies(dto.species());
            pet.setBreed(dto.breed());
            pet.setSize(dto.size());
            pet.setServiceType(dto.serviceType());
            pet.setClient(client.get());
            return repo.save(pet);
        }).orElseThrow(() -> {
            log.warn("Pet with id {} not found for update", id);
            return new NotFoundException("Pet not found with id: " + id);
        });
    }

    public void delete(final Long id) {
        log.info("Deleting pet with id: {}", id);
        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
        } else {
            log.warn("Pet with id {} not found for deletion", id);
            throw new NotFoundException("Pet not found with id: " + id);
        }
    }
}
