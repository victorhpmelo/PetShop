package br.start.petshop.entities;

import br.start.petshop.enums.GenderEnum;
import br.start.petshop.enums.ServiceEnum;
import br.start.petshop.enums.SizeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static jakarta.persistence.EnumType.STRING;
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

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;

    @Enumerated(STRING)
    private GenderEnum gender;

    private String species;

    private String breed;

    @Enumerated(STRING)
    private SizeEnum size;

    @Enumerated(STRING)
    private ServiceEnum serviceType;


}
