package br.start.petshop.controllers;

import br.start.petshop.DTOs.PetDTO;
import br.start.petshop.entities.Pet;
import br.start.petshop.repositories.PetRepository;
import br.start.petshop.services.PetService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("pets")
@AllArgsConstructor
public class PetController {

    private PetService service;
    private PetRepository repository;


    @GetMapping
    public List<Pet> getAllPets() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Pet getPetById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Pet createPet(@RequestBody PetDTO dto) {
        return service.save(dto);
    }

    @PutMapping("{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody PetDTO dto) {
        return service.update(id,dto);
    }

    @DeleteMapping("{id}")
    public void deletePet(@PathVariable Long id) {
        service.delete(id);
    }
}
