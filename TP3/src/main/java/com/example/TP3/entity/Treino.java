package com.example.TP3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workouts")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String focoPrincipal;

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    public Treino() {}

    public Treino(
        String nome,
        String focoPrincipal,
        Instrutor instrutor
    ) {
        this.nome = nome;
        this.focoPrincipal = focoPrincipal;
        this.instrutor = instrutor;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getFocoPrincipal() {
        return focoPrincipal;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }
} 
