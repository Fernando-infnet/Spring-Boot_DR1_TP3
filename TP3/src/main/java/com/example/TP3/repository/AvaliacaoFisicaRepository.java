package com.example.TP3.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.TP3.mongo.AvaliacaoFisica;

public interface AvaliacaoFisicaRepository extends MongoRepository<AvaliacaoFisica, String> {
    List<AvaliacaoFisica> findByAlunoId(Long alunoId);
}
