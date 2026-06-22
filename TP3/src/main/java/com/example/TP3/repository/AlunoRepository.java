package com.example.TP3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TP3.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByIsActiveTrue();
}
