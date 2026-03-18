# API de Agendamentos

API desenvolvida com Java + Spring Boot para gerenciamento de usuários e agendamentos, com validação de regras de negócio como conflito de horários.

## Funcionalidades

- Criar usuário
- Listar usuários
- Buscar usuário por ID
- Deletar usuário
- Criar agendamento
- Validação de e-mail único
- Validação de conflito de horário por usuário

## Regras de Negócio

- Não permite cadastrar dois usuários com o mesmo e-mail
- Não permite criar dois agendamentos no mesmo horário para o mesmo usuário
- Todo agendamento deve estar vinculado a um usuário existente

## Tecnologias

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de dados (PostgreSQL)
- Maven

## Estrutura do Projeto

- Controller → entrada da API
- Service → regras de negócio
- Repository → acesso ao banco
- Entity → modelo de dados

## Como executar

1. Clone o repositório
2. Abra no IntelliJ ou IDE de preferência
3. Execute a aplicação
4. Utilize o Postman para testar os endpoints

## Endpoints principais

### Usuários
- POST /usuarios
- GET /usuarios
- GET /usuarios/{id}
- DELETE /usuarios/{id}

### Agendamentos
- POST /agendamentos
- GET /agendamentos/lista
- GET /agendamentos/{id}
- DELETE /agendamentos/{id}

## Autor

Desenvolvido por Matheus Faias



# 📅 Scheduling API

Backend API built with Java and Spring Boot for managing users and appointments, including business rules such as schedule conflict validation.

## 🚀 Features

- Create user
- List users
- Get user by ID
- Delete user
- Create appointment
- Unique email validation
- Appointment conflict validation per user

## 🧠 Business Rules

- Users cannot share the same email
- A user cannot have two appointments at the same time
- Every appointment must be linked to an existing user

## 🛠️ Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Database (PostgreSQL)
- Maven

## 📌 Project Structure

- Controller → API entry point
- Service → business logic
- Repository → database access
- Entity → data model

## ▶️ How to run

1. Clone the repository
2. Open in IntelliJ or your preferred IDE
3. Run the application
4. Use Postman to test endpoints

## 📬 Main Endpoints

### Users
- POST /usuarios
- GET /usuarios
- GET /usuarios/{id}
- DELETE /usuarios/{id}

### Appointments
- POST /agendamentos
- GET /agendamentos/lista
- GET /agendamentos/{id}
- DELETE /agendamentos/{id}

## 💡 Author
Developed by Matheus Faias