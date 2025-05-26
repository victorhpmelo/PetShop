package br.start.petshop.repositories;

import br.start.petshop.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByClient_Id(Long clientId);
}
