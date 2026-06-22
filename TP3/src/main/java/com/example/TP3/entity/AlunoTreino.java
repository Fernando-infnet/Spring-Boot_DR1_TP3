package com.example.TP3.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_workouts")
public class AlunoTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Treino treino;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "completed")
    private Boolean completed = false;

    public AlunoTreino() {}

    public AlunoTreino(Aluno aluno, Treino treino, LocalDate startDate, Boolean completed) {
        this.aluno = aluno;
        this.treino = treino;
        this.startDate = startDate;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Treino getTreino() {
        return treino;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
