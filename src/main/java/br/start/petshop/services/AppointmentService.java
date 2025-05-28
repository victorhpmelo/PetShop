package br.start.petshop.services;

import br.start.petshop.DTOs.AppointmentDTO;
import br.start.petshop.entities.Appointment;
import br.start.petshop.entities.Clients;
import br.start.petshop.entities.Pet;
import br.start.petshop.exceptions.NotFoundException;
import br.start.petshop.exceptions.NullException;
import br.start.petshop.repositories.AppointmentRepository;
import br.start.petshop.repositories.ClientRepository;
import br.start.petshop.repositories.PetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AppointmentService {

    private AppointmentRepository repo;
    private ClientRepository clientRepo;
    private PetRepository petRepo;

    public Appointment toEntity(AppointmentDTO dto) {
        log.info("Attempting to convert AppointmentDTO to Appointment entity");

        Appointment appointment = new Appointment();
        Clients client = clientRepo.findById(dto.clientId())
                .orElseThrow(() -> new NotFoundException("Client not found with id: " + dto.clientId()));
        Pet pet = petRepo.findById(dto.petId())
                .orElseThrow(() -> new NotFoundException("Pet not found with id: " + dto.petId()));

        appointment.setDateTime(dto.appointmentDateTime());
        appointment.setClient(client);
        appointment.setPet(pet);
        appointment.setServiceType(dto.serviceType());

        if (!dto.notes().isEmpty()){
            appointment.setNotes(dto.notes());
        }

        log.info("Conversion was successful");
        return appointment;
    }


    public List<Appointment> getAll() {
        log.info("Fetching all appointments");
        List<Appointment> list = repo.findAll();
        if (!list.isEmpty()) {
            log.info("Found {} appointments", list.size());
            return list;
        }
        else {
            log.warn("No appointments found");
            throw new NotFoundException("No appointments found");
        }
    }

    public List<Appointment> getByClientId(Long clientId) {
        log.info("Fetching appointments for clientId: {}", clientId);
        List<Appointment> list = repo.findByClient_Id(clientId);
        if (!list.isEmpty()) {
            log.info("Found {} appointments with the clientId: {}", list.size(),clientId);
            return list;
        }
        log.warn("No appointments found for clientId: {}", clientId);
        throw new NotFoundException("No appointments found for this client");
    }

    public Appointment save(AppointmentDTO dto) {
        log.info("Attempting to create appointment for client with id: {}", dto.clientId());
        Appointment appointment = toEntity(dto);
        return repo.save(appointment);
    }


    public Appointment update(Long id, AppointmentDTO dto) {
        log.info("Updating appointment with id: {}", id);
        return repo.findById(id).map(existing -> {
            Optional<Clients> client = clientRepo.findById(dto.clientId());
            Optional<Pet> pet = petRepo.findById(dto.petId());
            existing.setDateTime(dto.appointmentDateTime());

            if (client.isPresent()) {
                log.info("Fetching client with id: {}", dto.clientId());
                existing.setClient(client.get());
            } else {
                log.info("Failed to fetch client with id: {}", dto.clientId());
                throw new NotFoundException("Failed to fetch client with id: " + dto.clientId());
            }

            if (pet.isPresent()) {
                log.info("Fetching pet with id: {}", dto.petId());
                existing.setPet(pet.get());
            } else {
                log.info("Failed to fetch pet with id: {}", dto.petId());
                throw new NotFoundException("Pet not found with id: " + dto.petId());
            }
            existing.setServiceType(dto.serviceType());
            return repo.save(existing);
        }).orElseThrow(() -> {
            log.warn("Appointment with id {} not found", id);
            return new NotFoundException("Appointment not found");
        });
    }

    public void delete(Long id) {
        log.info("Deleting appointment with id: {}", id);
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            log.warn("Appointment with id {} not found", id);
            throw new NotFoundException("Appointment not found");
        }
    }
}

