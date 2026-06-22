package com.example.TP3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TP3.entity.AlunoTreino;

public interface AlunoTreinoRepository extends JpaRepository<AlunoTreino, Long> {
    List<AlunoTreino> findByAlunoIdAndCompletedTrue(Long alunoId);

    @Query("select at.aluno.id, count(at) as cnt from AlunoTreino at where at.completed = true group by at.aluno.id order by cnt desc")
    java.util.List<Object[]> findRankingByCompletedCount();
}
