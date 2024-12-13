package com.example.eval.dao;

import com.example.eval.model.Convention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConventionDao extends JpaRepository<Convention, Integer> {
}
