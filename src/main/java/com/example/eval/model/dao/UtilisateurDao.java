package com.example.eval.model.dao;

import com.example.eval.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurDao extends JpaRepository <Utilisateur, Integer> {
}
