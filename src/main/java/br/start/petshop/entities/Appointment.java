package br.start.petshop.entities;

import br.start.petshop.enums.ServiceEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "appointment")
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateTime;

    @Enumerated(STRING)
    private ServiceEnum serviceType;

    private String notes;
}
