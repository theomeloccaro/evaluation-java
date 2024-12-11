package com.example.eval.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Convention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nom;
    float subvention;
    Integer salarie_maximum;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    Entreprise entreprise;

    @OneToMany(mappedBy = "convention")
    List<Salarie> salaries;
}

