package com.example.TP3.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.TP3.entity.Aluno;
import com.example.TP3.entity.AlunoTreino;
import com.example.TP3.entity.Instrutor;
import com.example.TP3.entity.Plano;
import com.example.TP3.entity.Treino;
import com.example.TP3.repository.AlunoRepository;
import com.example.TP3.repository.AlunoTreinoRepository;
import com.example.TP3.repository.InstrutorRepository;
import com.example.TP3.repository.PlanoRepository;
import com.example.TP3.repository.TreinoRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final PlanoRepository planoRepo;
    private final InstrutorRepository instrutorRepo;
    private final TreinoRepository treinoRepo;
    private final AlunoRepository alunoRepo;
    private final AlunoTreinoRepository alunoTreinoRepo;

    public DataLoader(PlanoRepository planoRepo,
                      InstrutorRepository instrutorRepo,
                      TreinoRepository treinoRepo,
                      AlunoRepository alunoRepo,
                      AlunoTreinoRepository alunoTreinoRepo) {
        this.planoRepo = planoRepo;
        this.instrutorRepo = instrutorRepo;
        this.treinoRepo = treinoRepo;
        this.alunoRepo = alunoRepo;
        this.alunoTreinoRepo = alunoTreinoRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        Random rnd = new Random(42);

        // Planos
        if (planoRepo.count() == 0) {
            planoRepo.save(new Plano("Básico", 49.90));
            planoRepo.save(new Plano("Premium", 99.90));
            planoRepo.save(new Plano("Plus", 69.90));
            log.info("Sample planos created");
        }

        // Instrutores
        if (instrutorRepo.count() == 0) {
            instrutorRepo.save(new Instrutor("Carlos Silva", "CREF123"));
            instrutorRepo.save(new Instrutor("Mariana Costa", "CREF456"));
            instrutorRepo.save(new Instrutor("Pedro Almeida", "CREF789"));
            log.info("Sample instrutores created");
        }

        List<Instrutor> instrutores = instrutorRepo.findAll();
        List<Plano> planos = planoRepo.findAll();

        // Treinos
        if (treinoRepo.count() == 0) {
            String[] nomes = {"Full Body", "Hipertrofia", "Cardio Intenso", "Alongamento"};
            String[] focos = {"Força", "Hipertrofia", "Resistência", "Flexibilidade"};
            for (int i = 0; i < nomes.length; i++) {
                Instrutor instr = instrutores.get(rnd.nextInt(instrutores.size()));
                treinoRepo.save(new Treino(nomes[i], focos[i], instr));
            }
            log.info("Sample treinos created");
        }

        List<Treino> treinos = treinoRepo.findAll();

        // Alunos
        if (alunoRepo.count() == 0) {
            String[] nomes = {
                "João Pereira", "Ana Souza", "Marcos Oliveira", "Beatriz Fernandes",
                "Lucas Gomes", "Carla Rodrigues", "Rafael Santos", "Mariana Lima",
                "Pedro Rocha", "Fernanda Dias"
            };
            String[] emails = {
                "joao.pereira@example.com", "ana.souza@example.com", "marcos.oliveira@example.com",
                "beatriz.fernandes@example.com", "lucas.gomes@example.com", "carla.rodrigues@example.com",
                "rafael.santos@example.com", "mariana.lima@example.com", "pedro.rocha@example.com",
                "fernanda.dias@example.com"
            };

            for (int i = 0; i < nomes.length; i++) {
                int year = 1970 + rnd.nextInt(36); 
                int month = 1 + rnd.nextInt(12);
                int day = 1 + rnd.nextInt(28);
                LocalDate bday = LocalDate.of(year, month, day);
                Plano plano = planos.get(rnd.nextInt(planos.size()));
                Aluno a = new Aluno(nomes[i], emails[i], Date.valueOf(bday), plano);
                if (rnd.nextDouble() < 0.15) {
                    try {
                        java.lang.reflect.Field f = Aluno.class.getDeclaredField("isActive");
                        f.setAccessible(true);
                        f.set(a, Boolean.FALSE);
                    } catch (Exception e) {
                        log.error("Error setting isActive to false for aluno: " + a.getName(), e);
                    }
                }
                alunoRepo.save(a);
            }
            log.info("Sample alunos created");
        }

        List<Aluno> alunos = alunoRepo.findAll();

        // AlunoTreino - history
        if (alunoTreinoRepo.count() == 0) {
            for (Aluno aluno : alunos) {
                int attempts = rnd.nextInt(6);
                for (int j = 0; j < attempts; j++) {
                    Treino t = treinos.get(rnd.nextInt(treinos.size()));
                    LocalDate start = LocalDate.now().minusDays(rnd.nextInt(365));
                    boolean completed = rnd.nextDouble() < 0.6; // 60% chance completed
                    AlunoTreino at = new AlunoTreino(aluno, t, start, completed);
                    alunoTreinoRepo.save(at);
                }
            }
            log.info("Sample aluno-treino history created");
        }

        log.info("DataLoader finished: planos={}, instrutores={}, treinos={}, alunos={}, alunoTreinos={}",
                planoRepo.count(), instrutorRepo.count(), treinoRepo.count(), alunoRepo.count(), alunoTreinoRepo.count());
    }
}
