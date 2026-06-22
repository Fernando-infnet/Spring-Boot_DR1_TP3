package com.example.TP3.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "instructors")
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cref;

    @OneToMany(mappedBy = "instrutor")
    private List<Treino> treinos = new ArrayList<>();

    public Instrutor() {}

    public Instrutor(String name, String cref) {
        this.name = name;
        this.cref = cref;
    }

    public Long getId() {
        return id;
    }
}