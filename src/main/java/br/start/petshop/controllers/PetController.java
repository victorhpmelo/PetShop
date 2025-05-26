package br.start.petshop.controllers;

import br.start.petshop.DTOs.PetDTO;
import br.start.petshop.entities.Pet;
import br.start.petshop.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("pets")
@AllArgsConstructor
@Tag(name = "CRUD Pets", description = "Operations to manage pets and services")
public class PetController {

    private final PetService service;

    @Operation(summary = "Return all pets registered in the database")
    @GetMapping
    public List<Pet> getAllPets() {
        log.info("Received request to list all pets");
        return service.getAll();
    }

    @Operation(summary = "Return a pet registered in the database by its id")
    @GetMapping("{id}")
    public Optional<Pet> getPetById(@PathVariable Long id) {
        log.info("Received request to fetch pet by ID: {}", id);
        return service.getById(id);
    }

    @Operation(summary = "Return all pets registered in the database by its Species")
    @GetMapping("species/{species}")
    public List<Pet> getPetById(@PathVariable String species) {
        log.info("Received request to fetch pets by species: {}", species);
        return service.getBySpecies(species);
    }

    @Operation(summary = "Register a pet in the database")
    @PostMapping
    @ResponseStatus(CREATED)
    public Pet createPet(@RequestBody PetDTO dto) {
        log.info("Received request to create a new pet");
        return service.save(dto);
    }

    @Operation(summary = "Alter the data of a pet in the database")
    @PutMapping("{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody PetDTO dto) {
        log.info("Received request to update pet with ID: {}", id);
        return service.update(id, dto);
    }

    @Operation(summary = "Delete a pet registered in the database")
    @DeleteMapping("{id}")
    public void deletePet(@PathVariable Long id) {
        log.info("Received request to delete pet with ID: {}", id);
        service.delete(id);
    }
}
