package br.start.petshop.controllers;

import br.start.petshop.DTOs.AppointmentDTO;
import br.start.petshop.entities.Appointment;
import br.start.petshop.services.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("appointments")
@AllArgsConstructor
@Tag(name = "CRUD Appointments", description = "Operations to manage appointments")
public class AppointmentController {

    private final AppointmentService service;

    @Operation(summary = "Return all appointments")
    @GetMapping
    public List<Appointment> getAllAppointments() {
        log.info("Received request to list all appointments");
        return service.getAll();
    }

    @Operation(summary = "Return all appointments for a specific client by client ID")
    @GetMapping("client/{clientId}")
    public List<Appointment> getAppointmentsByClientId(@PathVariable Long clientId) {
        log.info("Received request to find appointments by client ID: {}", clientId);
        return service.getByClientId(clientId);
    }

    @Operation(summary = "Register a new appointment")
    @PostMapping
    @ResponseStatus(CREATED)
    public Appointment createAppointment(@RequestBody AppointmentDTO dto) {
        log.info("Received request to create a new appointment");
        return service.save(dto);
    }

    @Operation(summary = "Update an existing appointment by ID")
    @PutMapping("{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO dto) {
        log.info("Received request to update appointment with ID: {}", id);
        return service.update(id, dto);
    }

    @Operation(summary = "Delete an appointment by ID")
    @DeleteMapping("{id}")
    public void deleteAppointment(@PathVariable Long id) {
        log.info("Received request to delete appointment with ID: {}", id);
        service.delete(id);
    }
}
