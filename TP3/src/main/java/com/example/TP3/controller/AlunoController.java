package com.example.TP3.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TP3.entity.Aluno;
import com.example.TP3.repository.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepo;

    public AlunoController(AlunoRepository alunoRepo) {
        this.alunoRepo = alunoRepo;
    }

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
        Aluno saved = alunoRepo.save(aluno);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Aluno>> listActive() {
        return ResponseEntity.ok(alunoRepo.findByIsActiveTrue());
    }
}
