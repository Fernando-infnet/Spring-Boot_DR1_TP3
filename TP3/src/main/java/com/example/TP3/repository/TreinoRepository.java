package com.example.TP3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TP3.entity.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
}
