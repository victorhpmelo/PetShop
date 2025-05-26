package br.start.petshop.services;

import br.start.petshop.DTOs.ClientDTO;
import br.start.petshop.entities.Clients;
import br.start.petshop.exceptions.NotFoundException;
import br.start.petshop.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ClientService {

    private ClientRepository repo;

    public Clients toEntity(ClientDTO dto) {
        log.info("Converting ClientDTO to Client entity: {}", dto.name());
        Clients client = new Clients();
        client.setName(dto.name());
        client.setEmail(dto.email());
        client.setPhone(dto.phone());
        return client;
    }

    public List<Clients> getAll() {
        log.info("Fetching all clients");
        List<Clients> clients = repo.findAll();
        if (!clients.isEmpty()) {
            return clients;
        }
        else {
            log.warn("No clients found");
            throw new NotFoundException("No clients found");
        }
    }

    public Optional<Clients> getById(Long id) {
        log.info("Fetching Client with id: {}", id);
        Optional<Clients> client = repo.findById(id);
        if (client.isPresent()) {
            log.info("Found Client with id: {}", id);
            return client;
        }
        else{
            log.warn("No client found with id: {}", id);
            throw new NotFoundException("No client found with id: " + id);
        }}

    public Optional<Clients> getByEmail(String email) {
        log.info("Fetching client by email: {}", email);
        Optional<Clients> clientByEmail = repo.findByEmail(email);
                 if(clientByEmail.isPresent()){
                     log.info("Found client with email: {}", email);
                     return clientByEmail;
                }
                 else {
                     log.warn("Client not found with email: {}", email);
                     throw new NotFoundException("Client not found");
                 }}

    public Clients save(ClientDTO dto) {
        log.info("Saving new client: {}", dto.name());
        Clients client = toEntity(dto);
        return repo.save(client);
    }

    public Clients update(Long id, ClientDTO dto) {
        log.info("Updating Clients with id: {}", id);
        return repo.findById(id).map(client -> {
            client.setName(dto.name());
            client.setEmail(dto.email());
            client.setPhone(dto.phone());
            return repo.save(client);
        }).orElseThrow(() -> {
            log.warn("Client with id {} not found", id);
            throw new NotFoundException("Client not found");
        });
    }

    public void delete(Long id) {
        log.info("Deleting client with id: {}", id);
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            log.warn("Client with id {} not found", id);
            throw new NotFoundException("Client not found");
        }
    }
}

