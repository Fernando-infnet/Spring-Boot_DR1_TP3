package com.example.TP3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TP3.entity.Instrutor;
import com.example.TP3.entity.Treino;
import com.example.TP3.repository.InstrutorRepository;
import com.example.TP3.repository.TreinoRepository;

@RestController
@RequestMapping("/treinos")
public class TreinoController {

    private final TreinoRepository treinoRepo;
    private final InstrutorRepository instrutorRepo;

    public TreinoController(TreinoRepository treinoRepo, InstrutorRepository instrutorRepo) {
        this.treinoRepo = treinoRepo;
        this.instrutorRepo = instrutorRepo;
    }

    @PostMapping
    public ResponseEntity<?> createTreino(@RequestBody Treino treino) {
        // ensure instrutor exists when id provided
        if (treino.getInstrutor() != null && treino.getInstrutor().getId() != null) {
            Instrutor instr = instrutorRepo.findById(treino.getInstrutor().getId()).orElse(null);
            if (instr == null) {
                return ResponseEntity.badRequest().body("Instrutor not found");
            }
            treino = new Treino(treino.getNome(), treino.getFocoPrincipal(), instr);
        }
        Treino saved = treinoRepo.save(treino);
        return ResponseEntity.ok(saved);
    }
}
