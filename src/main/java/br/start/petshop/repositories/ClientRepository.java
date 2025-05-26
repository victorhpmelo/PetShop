package br.start.petshop.repositories;

import br.start.petshop.entities.Clients;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Clients, Long> {

    Optional<Clients> findByEmail(@Email String email);
}
