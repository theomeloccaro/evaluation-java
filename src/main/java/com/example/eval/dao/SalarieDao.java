package com.example.eval.dao;

import com.example.eval.model.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalarieDao extends JpaRepository<Salarie, Integer> {
}
