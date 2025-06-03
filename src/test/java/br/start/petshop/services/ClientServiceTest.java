package br.start.petshop.services;

import br.start.petshop.DTOs.ClientDTO;
import br.start.petshop.entities.Appointment;
import br.start.petshop.entities.Clients;
import br.start.petshop.entities.Pet;
import br.start.petshop.exceptions.NotFoundException;
import br.start.petshop.repositories.AppointmentRepository;
import br.start.petshop.repositories.ClientRepository;
import br.start.petshop.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private ClientService clientService;

    private ClientDTO clientDTO;
    private Clients client;
    private List<Pet> pets;
    private List<Appointment> appointments;

    @BeforeEach
    void setUp() {
        clientDTO = new ClientDTO(
                1L,
                "John Doe",
                "john@example.com",
                "1234567890",
                List.of(1L),
                List.of(1L)
        );

        client = new Clients();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john@example.com");
        client.setPhone("1234567890");

        pets = List.of(new Pet());
        appointments = List.of(new Appointment());
    }

    @Test
    void save_ShouldReturnSavedClient() {
        when(petRepository.findAllById(clientDTO.petIds())).thenReturn(pets);
        when(appointmentRepository.findAllById(clientDTO.appointmentIds())).thenReturn(appointments);
        when(clientRepository.save(any(Clients.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Clients saved = clientService.save(clientDTO);

        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo("John Doe");
        verify(clientRepository).save(any(Clients.class));
    }

    @Test
    void getById_ShouldReturnClient() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        Clients found = clientService.getById(1L).orElseThrow();

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(1L);
        verify(clientRepository).findById(1L);
    }

    @Test
    void getById_ShouldThrowException_WhenNotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> clientService.getById(1L));
        verify(clientRepository).findById(1L);
    }

    @Test
    void getAll_ShouldReturnClients() {
        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Clients> result = clientService.getAll();

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getName()).isEqualTo("John Doe");
        verify(clientRepository).findAll();
    }

    @Test
    void delete_ShouldDeleteClient_WhenExists() {
        when(clientRepository.existsById(1L)).thenReturn(true);

        clientService.delete(1L);

        verify(clientRepository).deleteById(1L);
    }

    @Test
    void delete_ShouldThrow_WhenClientNotExists() {
        when(clientRepository.existsById(1L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> clientService.delete(1L));
    }
}
