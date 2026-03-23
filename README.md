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
* Diferenciação de permissões entre ROLE_USER (Clientes) e ROLE_ADMIN (Administradores).

---

## Tecnologias

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
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
### Acesso Administrativo (Data Seeding)
Ao iniciar a aplicação, um administrador padrão é criado automaticamente:

Usuário: admin@plataforma.com

Senha: Admin@123456

---
### Autenticação (Login)
Método: POST
URL: http://localhost:8080/auth/login

Body (JSON):

```json
{ "email": "admin@plataforma.com", 
  "senha": "Admin@123456" }
```
---
Copie o campo token retornado, configurar o Token nas outras rotas.

No Postman, vá na aba Authorization, selecione Type: Bearer Token.

Cole o token no campo Token.

--- 

###  Criar agendamento

```json
POST /agendamentos

{
  "dataHora": "2026-03-20T14:00:00",
  "descricao": "Consulta"
}
```

---

###  Atualizar agendamento

```json
PUT /agendamentos/1

{
  "dataHora": "2026-03-25T15:30:00",
  "descricao": "Consulta atualizada",
  "userId": 1
}
```
### Listar com Paginação (Admin)
```json
GET/agendamento/todos

URL: http://localhost:8080/agendamentos/todos

Destaque: O retorno incluirá o objeto content com os dados e metadados como totalElements e totalPages.
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

