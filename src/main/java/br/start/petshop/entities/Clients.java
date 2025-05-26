package br.start.petshop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Clients {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Email
    private String email;

    private String phone;

    @OneToMany(mappedBy = "clients", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets;
}
