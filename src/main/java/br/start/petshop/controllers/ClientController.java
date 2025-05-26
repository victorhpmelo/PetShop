package br.start.petshop.controllers;

import br.start.petshop.DTOs.ClientDTO;
import br.start.petshop.entities.Clients;
import br.start.petshop.services.ClientService;
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
@RequestMapping("clients")
@AllArgsConstructor
@Tag(name = "CRUD Clients", description = "Operations to manage clients")
public class ClientController {

    private final ClientService service;

    @Operation(summary = "Return all clients registered in the database")
    @GetMapping
    public List<Clients> getAllClients() {
        log.info("Received request to list all clients");
        return service.getAll();
    }

    @Operation(summary = "Return a client by its ID")
    @GetMapping("{id}")
    public Optional<Clients> getClientById(@PathVariable Long id) {
        log.info("Received request to find client by ID: {}", id);
        return service.getById(id);
    }

    @Operation(summary = "Return a client by email")
    @GetMapping("email/{email}")
    public Optional<Clients> getClientByEmail(@PathVariable String email) {
        log.info("Received request to find client by email: {}", email);
        return service.getByEmail(email);
    }

    @Operation(summary = "Register a new client")
    @PostMapping
    @ResponseStatus(CREATED)
    public Clients createClient(@RequestBody ClientDTO dto) {
        log.info("Received request to create new client: {}", dto.name());
        return service.save(dto);
    }

    @Operation(summary = "Update an existing client")
    @PutMapping("{id}")
    public Clients updateClient(@PathVariable Long id, @RequestBody ClientDTO dto) {
        log.info("Received request to update client with ID: {}", id);
        return service.update(id, dto);
    }

    @Operation(summary = "Delete a client by its ID")
    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable Long id) {
        log.info("Received request to delete client with ID: {}", id);
        service.delete(id);
    }
}

