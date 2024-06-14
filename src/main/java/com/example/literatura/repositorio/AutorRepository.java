package com.example.literatura.repositorio;

import com.example.literatura.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    List<AutorEntity> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(int year, int year2);
}