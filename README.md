# Spring-Boot_DR1_TP3

---

## Visão Geral
Esta aplicação modela uma academia simples:
- `Aluno` tem um `Plano` e pode completar `Treino`s.
- `Instrutor` ministra `Treino`s.
- Entidade JPA de junção `AlunoTreino` registra quando um aluno iniciou/completou um treino.

O `DataLoader` popula o banco H2 com planos, instrutores, treinos, alunos e histórico de treinos usando nomes e dados em português.

---

## Tecnologias
- Java 21
- Spring Boot 4.x
- Spring Data JPA (Hibernate) + H2 in-memory database
- Spring Web (REST controllers)
- Spring Data MongoDB (documento `AvaliacaoFisica`) — opcional
- Spring Data Redis (tokens) — opcional
- Maven (mvnw)

---

## Como executar
Pré-requisitos: `JDK 21`, `Docker` (opcional para Mongo/Redis), `Git`.

1) Buildar o projeto:

```bash
cd TP3
./mvnw -DskipTests package
```

2) Rodar localmente:

```bash
./mvnw -DskipTests spring-boot:run
```

---

## Endpoints principais
- POST /alunos — criar um `Aluno` (body JSON com `name`, `plano` ou `planoId`).
- POST /treinos — criar um `Treino` associado a um `Instrutor` (body com `nome`, `focoPrincipal`, `instrutorId`).
- GET /alunos/ativos — lista alunos ativos (JSON). Exibe `id`, `name` e `plano` resumido.
- GET /ranking — retorna ranking de alunos por número de treinos finalizados.
- POST /avaliacoes — (Mongo) criar `AvaliacaoFisica` para um aluno.
- POST /tokens/generate/{alunoId} — (Redis) gerar token temporário para aluno.
- GET /actuator/health — health checks (H2, Mongo, Redis conforme configuração).

Consulte os controladores em `src/main/java/com/example/TP3/controller` para detalhes de payloads e rotas.

---

## Exemplos de JSON retornados
Exemplo de resposta para `GET /alunos/ativos` (lista encurtada):

```json
[
  {
    "name": "João Pereira",
    "plano": {
      "planName": "Plus",
      "price": 69.9,
      "id": 3
    },
    "id": 1
  },
  {
    "name": "Ana Souza",
    "plano": {
      "planName": "Básico",
      "price": 49.9,
      "id": 1
    },
    "id": 2
  }
]
```

Exemplo de `GET /ranking` (top alunos por treinos completados):

```json
[
    {
        "alunoId": 3,
        "alunoName": "Marcos Oliveira",
        "completions": 4
    },
    {
        "alunoId": 5,
        "alunoName": "Lucas Gomes",
        "completions": 3
    }
]
```

---

## Capturas e o que cada imagem mostra

POST /alunos — captura do fluxo de criação de um `Aluno` (request JSON e response):

<img width="704" height="619" alt="POST /alunos" src="https://github.com/user-attachments/assets/5ef4abaa-0fea-43b6-9a83-d245c334651c" />

Descrição: mostra o corpo enviado (nome, plano) e o JSON de resposta com o `id` atribuído pelo banco.

POST /treinos — captura do fluxo de criação de um `Treino` vinculado a um `Instrutor`:

<img width="704" height="619" alt="POST /treinos" src="https://github.com/user-attachments/assets/4059a6a4-d64b-4f1e-96b4-2771a22e08dc" />

Descrição: request/response ao criar um `Treino`. Útil para verificar validação e relacionamento com `Instrutor`.

GET /alunos/ativos — resposta JSON completa retornada:

<img width="707" height="252" alt="GET /alunos/ativos JSON" src="https://github.com/user-attachments/assets/c94d5963-5ce4-4baa-b0dc-3ae52a2528cc" />

Descrição: lista de alunos ativos com seus planos. O JSON completo de exemplo está acima.

GET /ranking — captura mostrando o resultado agregação por completions:

<img width="707" height="252" alt="GET /ranking" src="https://github.com/user-attachments/assets/088b9066-e729-4990-a81f-930322537c35" />

Descrição: ranking de alunos ordenado por número de treinos finalizados. Use para dashboards simples.

Actuator `/actuator/health` — print do health (pode aparecer UP ou DOWN dependendo do Mongo/Redis):

<img width="702" height="592" alt="Actuator health" src="https://github.com/user-attachments/assets/4c28cbc1-8f73-4c8d-9a26-a849fdf9661c" />

Descrição: mostra componentes de saúde. Se Mongo/Redis não estiverem rodando, a seção correspondente aparecerá como DOWN — veja instruções acima para iniciar via Docker ou desabilitar checagens.

H2 Console — prints mostrando tabelas e dados carregados pelo `DataLoader`:

<img width="576" height="737" alt="H2 - tables" src="https://github.com/user-attachments/assets/9bf6b832-ce84-4385-b982-412996aa16dc" />

<img width="576" height="736" alt="H2 - plans" src="https://github.com/user-attachments/assets/9c7ab147-69ff-4827-afd7-87129582b6ab" />

Descrição: verifique as tabelas `aluno`, `plano`, `instrutor`, `treino` e `aluno_treino` e confirme que os dados de exemplo foram inseridos.

---

## Próximos passos sugeridos
- Gerar um PDF com essas capturas (posso montar se quiser).
- Adicionar DTOs e validação para endpoints.
- Incluir testes de integração para os fluxos principais.

---

Se quiser que eu gere o PDF com as imagens nesta ordem e inclua legendas, ou se preferir que eu desative as health checks do Mongo/Redis automaticamente, diga qual opção prefere e eu aplico a alteração em seguida.