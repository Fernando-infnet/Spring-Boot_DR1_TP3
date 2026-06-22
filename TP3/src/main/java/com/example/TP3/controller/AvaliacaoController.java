package com.example.TP3.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TP3.mongo.AvaliacaoFisica;
import com.example.TP3.repository.AvaliacaoFisicaRepository;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoFisicaRepository repo;

    public AvaliacaoController(AvaliacaoFisicaRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<AvaliacaoFisica> create(@RequestBody AvaliacaoFisica a) {
        return ResponseEntity.ok(repo.save(a));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<AvaliacaoFisica>> listByAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(repo.findByAlunoId(alunoId));
    }
}
