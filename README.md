#  Booking API

API REST desenvolvida com **Java + Spring Boot** para gerenciamento de usuários e agendamentos.

---

##  Funcionalidades

* Criar usuário
* Listar usuários
* Atualizar usuário
* Deletar usuário
* Criar agendamento
* Atualizar agendamento
* Validação de conflito de horário
* Tratamento global de exceções
* Uso de DTO (Data Transfer Object)

---

## Tecnologias

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL / H2
* Maven

---

##  Exemplo de Requisições

###  Criar usuário

```json
POST /usuarios

{
  "name": "Matheus",
  "email": "matheus@email.com",
  "senha": "123456"
}
```

---

###  Criar agendamento

```json
POST /agendamentos

{
  "dataHora": "2026-03-20T14:00:00",
  "descricao": "Consulta",
  "userId": 1
}
```

---

### ✏ Atualizar agendamento

```json
PUT /agendamentos/1

{
  "dataHora": "2026-03-25T15:30:00",
  "descricao": "Consulta atualizada",
  "userId": 1
}
```

---

##  Arquitetura

O projeto segue o padrão em camadas:

* **Controller** → entrada da API
* **Service** → regras de negócio
* **Repository** → acesso ao banco
* **Entity** → modelo do banco
* **DTO** → comunicação da API
* **Exception/Handler** → tratamento de erros

---

##  English Version

###  Booking API

REST API built with **Java + Spring Boot** for managing users and appointments.

---

###  Features

* Create users
* List users
* Update users
* Delete users
* Create appointments
* Update appointments
* Schedule conflict validation
* Global exception handling
* DTO pattern usage

---

###  Technologies

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL / H2
* Maven

---

###  Request Examples

#### Create User

```json
POST /usuarios

{
  "name": "Matheus",
  "email": "matheus@email.com",
  "senha": "123456"
}
```

---

#### Create Appointment

```json
POST /agendamentos

{
  "dataHora": "2026-03-20T14:00:00",
  "descricao": "Consulta",
  "userId": 1
}
```

---

#### Update Appointment

```json
PUT /agendamentos/1

{
  "dataHora": "2026-03-25T15:30:00",
  "descricao": "Updated appointment",
  "userId": 1
}
```
