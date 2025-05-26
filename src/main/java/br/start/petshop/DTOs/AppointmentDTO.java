package br.start.petshop.DTOs;

import br.start.petshop.enums.ServiceEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDTO(
        Long id,

        @NotNull(message = "clientId fild is mandatory")
        Long clientId,

        @NotNull(message = "petId field is mandatory")
        Long petId,

        @NotNull(message = "Appointment date and time is mandatory")
        @Future(message = "Appointment must be scheduled for a future date and time")
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime appointmentDateTime,

        @NotNull(message = "Service field is mandatory")
        ServiceEnum serviceType,

        String notes
) {}
