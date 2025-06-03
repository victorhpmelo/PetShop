package br.start.petshop.services;

import br.start.petshop.DTOs.AppointmentDTO;
import br.start.petshop.entities.Appointment;
import br.start.petshop.entities.Clients;
import br.start.petshop.entities.Pet;
import br.start.petshop.enums.ServiceEnum;
import br.start.petshop.repositories.AppointmentRepository;
import br.start.petshop.repositories.ClientRepository;
import br.start.petshop.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    private AppointmentDTO appointmentDTO;
    private Clients client;
    private Pet pet;

    @BeforeEach
    void setUp() {
        client = new Clients();
        client.setId(1L);
        client.setName("John Doe");

        pet = new Pet();
        pet.setId(1L);
        pet.setName("Buddy");

        appointmentDTO = new AppointmentDTO(
                1L,
                1L,
                1L,
                LocalDateTime.now().plusDays(1),
                ServiceEnum.GROOMING,
                "Regular grooming session"
        );
    }

    @Test
    void save_ShouldReturnSavedAppointment() {
        // Arrange
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(appointmentRepository.save(any(Appointment.class))).thenAnswer(invocation -> {
            Appointment appt = invocation.getArgument(0);
            appt.setId(99L); // simulate DB ID generation
            return appt;
        });

        // Act
        Appointment savedAppointment = appointmentService.save(appointmentDTO);

        // Assert
        assertThat(savedAppointment).isNotNull();
        assertThat(savedAppointment.getClient()).isEqualTo(client);
        assertThat(savedAppointment.getPet()).isEqualTo(pet);
        assertThat(savedAppointment.getServiceType()).isEqualTo(ServiceEnum.GROOMING);
        assertThat(savedAppointment.getNotes()).isEqualTo("Regular grooming session");

        verify(clientRepository).findById(1L);
        verify(petRepository).findById(1L);
        verify(appointmentRepository).save(any(Appointment.class));
    }
}
