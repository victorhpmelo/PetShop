package br.start.petshop.repositories;

import br.start.petshop.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IPetRepository extends JpaRepository<Pet, Long> {

}
