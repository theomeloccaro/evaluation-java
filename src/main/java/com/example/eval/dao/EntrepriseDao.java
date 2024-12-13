package com.example.eval.dao;

import com.example.eval.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseDao extends JpaRepository<Entreprise, Integer> {
}
