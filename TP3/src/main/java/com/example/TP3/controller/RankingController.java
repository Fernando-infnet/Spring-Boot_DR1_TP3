package com.example.TP3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TP3.repository.AlunoRepository;
import com.example.TP3.repository.AlunoTreinoRepository;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    private final AlunoTreinoRepository alunoTreinoRepo;
    private final AlunoRepository alunoRepo;

    public RankingController(AlunoTreinoRepository alunoTreinoRepo, AlunoRepository alunoRepo) {
        this.alunoTreinoRepo = alunoTreinoRepo;
        this.alunoRepo = alunoRepo;
    }

    @GetMapping
    public ResponseEntity<List<RankingItem>> getRanking() {
        List<Object[]> raw = alunoTreinoRepo.findRankingByCompletedCount();
        List<RankingItem> result = new ArrayList<>();
        for (Object[] row : raw) {
            Long alunoId = (Long) row[0];
            Long count = (Long) row[1];
            var aluno = alunoRepo.findById(alunoId).orElse(null);
            if (aluno != null) {
                result.add(new RankingItem(aluno.getId(), aluno.getName(), count));
            }
        }
        return ResponseEntity.ok(result);
    }

    public static class RankingItem {
        public Long alunoId;
        public String alunoName;
        public Long completions;

        public RankingItem(Long alunoId, String alunoName, Long completions) {
            this.alunoId = alunoId;
            this.alunoName = alunoName;
            this.completions = completions;
        }
    }
}
