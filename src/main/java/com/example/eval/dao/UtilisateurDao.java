package com.example.eval.dao;

import com.example.eval.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurDao extends JpaRepository <Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String pseudo);
}
