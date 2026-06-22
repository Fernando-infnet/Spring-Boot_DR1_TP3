package com.example.TP3.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "avaliacoes")
public class AvaliacaoFisica {

    @Id
    private String id;
    private Long alunoId;
    private Double peso;
    private Double altura;
    private Double percentualGordura;
    private String anotacoes;

    public AvaliacaoFisica() {}

    public AvaliacaoFisica(Long alunoId, Double peso, Double altura, Double percentualGordura, String anotacoes) {
        this.alunoId = alunoId;
        this.peso = peso;
        this.altura = altura;
        this.percentualGordura = percentualGordura;
        this.anotacoes = anotacoes;
    }

    public String getId() {
        return id;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getAltura() {
        return altura;
    }

    public Double getPercentualGordura() {
        return percentualGordura;
    }

    public String getAnotacoes() {
        return anotacoes;
    }
}
