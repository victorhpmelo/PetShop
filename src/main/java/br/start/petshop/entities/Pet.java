package br.start.petshop.entities;

import br.start.petshop.enums.SizeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private Long age;

    private String gender;

    private String species;

    @Enumerated(EnumType.STRING)
    private SizeEnum size;
}
