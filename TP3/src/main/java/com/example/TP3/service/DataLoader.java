package com.example.TP3.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.TP3.entity.Instrutor;
import com.example.TP3.entity.Plano;
import com.example.TP3.repository.InstrutorRepository;
import com.example.TP3.repository.PlanoRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlanoRepository planoRepo;
    private final InstrutorRepository instrutorRepo;

    public DataLoader(PlanoRepository planoRepo, InstrutorRepository instrutorRepo) {
        this.planoRepo = planoRepo;
        this.instrutorRepo = instrutorRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (planoRepo.count() == 0) {
            planoRepo.save(new Plano("Basico", 49.90));
            planoRepo.save(new Plano("Premium", 99.90));
        }
        if (instrutorRepo.count() == 0) {
            instrutorRepo.save(new Instrutor("Carlos", "CREF123"));
            instrutorRepo.save(new Instrutor("Mariana", "CREF456"));
        }
    }
}
