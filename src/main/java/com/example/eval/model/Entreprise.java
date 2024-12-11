package com.example.eval.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nom;

    @OneToOne
    @JoinColumn(name = "utilisateur_id", unique = true)
    Utilisateur utilisateur;

    @OneToMany(mappedBy = "entreprise")
    List<Convention> conventions;
}