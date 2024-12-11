package com.example.eval.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Salarie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String matricule;
    String code_barre;

    @ManyToOne
    @JoinColumn(name = "convention_id")
    Convention convention;
}
