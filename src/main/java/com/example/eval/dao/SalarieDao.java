package com.example.eval.dao;

import com.example.eval.model.Salarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalarieDao extends JpaRepository<Salarie, Integer> {
    @Query("SELECT COUNT(s) FROM Salarie s WHERE s.convention.id = :conventionId")
    int countByConventionId(Integer conventionId);
}
