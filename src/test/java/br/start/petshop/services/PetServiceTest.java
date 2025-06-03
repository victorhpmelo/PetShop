package br.start.petshop.services;

import br.start.petshop.DTOs.PetDTO;
import br.start.petshop.entities.Clients;
import br.start.petshop.entities.Pet;
import br.start.petshop.enums.GenderEnum;
import br.start.petshop.enums.ServiceEnum;
import br.start.petshop.enums.SizeEnum;
import br.start.petshop.exceptions.NotFoundException;
import br.start.petshop.repositories.ClientRepository;
import br.start.petshop.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private PetService petService;

    private Pet pet;
    private PetDTO petDTO;
    private Clients client;

    @BeforeEach
    void setUp() {
        client = new Clients();
        client.setId(1L);

        pet = new Pet();
        pet.setId(1L);
        pet.setName("Buddy");
        pet.setClient(client);

        petDTO = new PetDTO(
                1L,
                "Buddy",
                new Date(2020, 1, 1),
                GenderEnum.M,
                "Dog",
                "Labrador",
                SizeEnum.L,
                ServiceEnum.GROOMING,
                1L
        );
    }

    @Test
    void save_ShouldReturnSavedPet() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(petRepository.save(any(Pet.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pet saved = petService.save(petDTO);

        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo("Buddy");
        verify(petRepository).save(any(Pet.class));
    }

    @Test
    void getById_ShouldReturnPet() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Pet found = petService.getById(1L).orElseThrow();

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(1L);
        verify(petRepository).findById(1L);
    }

    @Test
    void getById_ShouldThrow_WhenNotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> petService.getById(1L));
    }

    @Test
    void getAll_ShouldReturnListOfPets() {
        when(petRepository.findAll()).thenReturn(List.of(pet));

        List<Pet> result = petService.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Buddy");
        verify(petRepository).findAll();
    }

    @Test
    void delete_ShouldDeletePet_WhenExists() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        petService.delete(1L);

        verify(petRepository).deleteById(1L);
    }

    @Test
    void delete_ShouldThrow_WhenNotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> petService.delete(1L));
    }

    @Test
    void update_ShouldModifyPet_WhenExists() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(petRepository.save(any(Pet.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pet updated = petService.update(1L, petDTO);

        assertThat(updated).isNotNull();
        assertThat(updated.getName()).isEqualTo("Buddy");
        verify(petRepository).save(any(Pet.class));
    }

    @Test
    void update_ShouldThrow_WhenPetNotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> petService.update(1L, petDTO));
    }
}
