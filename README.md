# ThinkDesk Tenancy REST API

## Visão Geral

O **ThinkDesk** é uma API REST multi-inquilino (multi-tenant) projetada para gerenciar um sistema de suporte e atendimento, como um helpdesk ou ITSM (IT Service Management). A API permite que diferentes "inquilinos" (tenants) operem de forma isolada, cada um com seus próprios usuários, técnicos, tickets e políticas de serviço.

A aplicação foi construída utilizando Java e Spring Boot, seguindo os princípios da **Arquitetura Limpa (Clean Architecture)** para garantir uma separação clara de responsabilidades, alta testabilidade e manutenibilidade.

---

## Arquitetura

O projeto é dividido em três camadas principais, refletindo os princípios da Arquitetura Limpa e da **Arquitetura Hexagonal (Ports and Adapters)**. O objetivo é isolar o núcleo de negócio (`Domain` e `Application`) das dependências externas (`Infra`).

- **`/src/main/java/ThinkDesk/Domain`**: O coração da aplicação (o "Hexágono"). Esta camada contém os modelos de negócio (Entidades), as regras de negócio centrais e as interfaces dos repositórios (as "Portas"). Não possui dependências de frameworks externos.

- **`/src/main/java/ThinkDesk/Application`**: A camada de casos de uso. Ela orquestra o fluxo de dados entre a camada de interface e a de domínio. Contém os `Services` da aplicação, os `Controllers` e os `DTOs`.

- **`/src/main/java/ThinkDesk/Infra`**: A camada mais externa, contendo os "Adaptadores". Aqui ficam as implementações das "Portas" (como repositórios JPA), a configuração de segurança (Spring Security), e outras configurações de infraestrutura que conectam o núcleo da aplicação ao mundo exterior.

---

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**: Framework principal para a construção da aplicação.
- **Spring Data JPA**: Para persistência de dados e implementação dos repositórios.
- **Hibernate**: Implementação da JPA para o mapeamento objeto-relacional.
- **MySQL**: Banco de dados relacional.
- **Spring Security**: Para autenticação e autorização.
- **JWT (JSON Web Tokens)**: Para a segurança de endpoints stateless.
- **Lombok**: Para reduzir código boilerplate.

---

## Enumerações (Valores Válidos)

Ao enviar dados para a API, utilize os seguintes valores (strings) para os campos que esperam uma enumeração:

- **`TicketStatus`**: Status de um ticket.
  - `OPEN`
  - `AWAITINGCUSTOMER`
  - `RESOLVED`
  - `CLOSED`

- **`TicketCategory`**: Categoria de um ticket.
  - `SERVICEREQUEST`
  - `PROBLEM`
  - `INCIDENT`

- **`TicketPriority`**: Prioridade de um ticket.
  - `URGENT`
  - `HIGH`
  - `MEDIUM`
  - `LOW`

- **`TechnicianLevel`**: Nível de especialização de um técnico.
  - `L1`
  - `L2`
  - `Specialist`

---

## Endpoints da API

A autenticação é necessária para a maioria dos endpoints e é feita através de um Bearer Token JWT no header `Authorization`.

### 1. Autenticação

**Controller:** `LoginController`

- **`POST /login`**
  - **Descrição:** Autentica um `User` ou `Technician` e retorna um token JWT.
  - **Request Body:**
    ```json
    {
      "login": "user@example.com",
      "password": "password123"
    }
    ```
  - **Response Body (200 OK):**
    ```json
    {
      "token": "ey...[jwt_token]..."
    }
    ```

---

### 2. Tenants

**Controller:** `TenantController`

- **`GET /tenants`**
  - **Descrição:** Lista todos os tenants cadastrados.
  - **Response Body (200 OK):** `List<Tenant>`

- **`POST /tenants`**
  - **Descrição:** Cria um novo tenant.
  - **Request Body:**
    ```json
    {
      "tradingName": "Empresa Exemplo",
      "taxID": "12.345.678/0001-99",
      "createdAt": "2025-10-07T10:00:00",
      "settings": "{\"theme\":\"dark\"}"
    }
    ```
  - **Response Body (200 OK):** `Tenant`

- **`PUT /tenants/{id}`**
  - **Descrição:** Atualiza um tenant existente.
  - **Request Body:** (Mesma estrutura do `POST`)

- **`DELETE /tenants/{id}`**
  - **Descrição:** Deleta um tenant.
  - **Response (204 No Content)**

---

### 3. Usuários (Users)

**Controller:** `UserController`

- **`GET /users`**
  - **Descrição:** Lista todos os usuários.
  - **Response Body (200 OK):** `List<User>`

- **`GET /users/{id}`**
  - **Descrição:** Busca um usuário por ID.
  - **Response Body (200 OK):** `User`

- **`POST /users`**
  - **Descrição:** Cria um novo usuário.
  - **Request Body:**
    ```json
    {
      "name": "Nome do Usuário",
      "email": "usuario@exemplo.com",
      "password": "password123",
      "position": "Analista",
      "active": true,
      "tenantId": 1
    }
    ```
  - **Response Body (200 OK):** `User`

- **`PUT /users/{id}`**
  - **Descrição:** Atualiza um usuário existente.
  - **Request Body:** (Mesma estrutura do `POST`)

- **`DELETE /users/{id}`**
  - **Descrição:** Deleta um usuário.
  - **Response (204 No Content)**

---

### 4. Técnicos (Technicians)

**Controller:** `TechnicianController`

- **`GET /technicians`**
  - **Descrição:** Lista todos os técnicos.
  - **Response Body (200 OK):** `List<Technician>`

- **`GET /technicians/{id}`**
  - **Descrição:** Busca um técnico por ID.
  - **Response Body (200 OK):** `Technician`

- **`POST /technicians`**
  - **Descrição:** Cria um novo técnico.
  - **Request Body:**
    ```json
    {
      "id": null,
      "name": "Nome do Técnico",
      "email": "tecnico@exemplo.com",
      "password": "password123",
      "level": "L1",
      "active": true,
      "tenant": { "id": 1 }
    }
    ```
  - **Response Body (200 OK):** `Technician`

- **`PUT /technicians/{id}`**
  - **Descrição:** Atualiza um técnico existente.
  - **Request Body:** (Mesma estrutura do `POST`)

- **`DELETE /technicians/{id}`**
  - **Descrição:** Deleta um técnico.
  - **Response (204 No Content)**

---

### 5. Tickets

**Controller:** `TicketController`

- **`GET /tickets`**
  - **Descrição:** Lista todos os tickets de forma paginada.
  - **Response Body (200 OK):** `Page<Ticket>`

- **`GET /tickets/{id}`**
  - **Descrição:** Busca um ticket por ID.
  - **Response Body (200 OK):** `Ticket`

- **`POST /tickets`**
  - **Descrição:** Cria um novo ticket.
  - **Request Body:**
    ```json
    {
      "title": "Problema com a impressora",
      "description": "A impressora do segundo andar não está funcionando.",
      "priority": "MEDIUM",
      "resolutionDueDate": "2025-10-08T18:00:00",
      "category": "HARDWARE",
      "technician": { "id": 1 },
      "tenant": { "id": 1 }
    }
    ```
  - **Response Body (200 OK):** `Ticket`

- **`PUT /tickets/{id}`**
  - **Descrição:** Atualiza um ticket existente.
  - **Request Body:** (Mesma estrutura do `POST`)

- **`DELETE /tickets/{id}`**
  - **Descrição:** Deleta um ticket.
  - **Response (204 No Content)**

---

### 6. Histórico de Tickets (TicketLog)

**Controller:** `TicketLogController`

- **`GET /ticketlog`**
  - **Descrição:** Lista todos os logs de tickets.
  - **Response Body (200 OK):** `List<TicketLog>`

- **`POST /ticketlog`**
  - **Descrição:** Adiciona uma nova entrada de log a um ticket.
  - **Request Body:**
    ```json
    {
      "content": ["Técnico verificou o problema.", "Escalado para o N2."],
      "createdAt": "2025-10-07T11:00:00",
      "ticket_id": 1,
      "isPrivate": false
    }
    ```
  - **Response Body (200 OK):** `TicketLog`

- **`PUT /ticketlog/{id}`**
  - **Descrição:** Atualiza uma entrada de log.
  - **Request Body:** (Mesma estrutura do `POST`)

- **`DELETE /ticketlog/{id}`**
  - **Descrição:** Deleta uma entrada de log.
  - **Response (204 No Content)**

---

### 7. Políticas de SLA (SLA Policies)

**Controller:** `SlaPolicyController`

- **`GET /slapolicies`**
  - **Descrição:** Lista todas as políticas de SLA de forma paginada.
  - **Response Body (200 OK):** `Page<SlaPolicyResponseDTO>`

- **`GET /slapolicies/{id}`**
  - **Descrição:** Busca uma política por ID.
  - **Response Body (200 OK):** `SlaPolicyResponseDTO`

- **`GET /slapolicies/tenant/{tenantId}`**
  - **Descrição:** Lista todas as políticas de um tenant específico.
  - **Response Body (200 OK):** `Page<SlaPolicyResponseDTO>`

- **`POST /slapolicies`**
  - **Descrição:** Cria uma nova política de SLA.
  - **Request Body:**
    ```json
    {
      "name": "SLA Padrão - Prioridade Alta",
      "responseTimeMinutes": 60,
      "resolutionTimeMinutes": 240,
      "operationalHoursOnly": true,
      "isActive": true,
      "priority": "HIGH",
      "tenantId": 1
    }
    ```
  - **Response Body (200 OK):** `SlaPolicyResponseDTO`

- **`PUT /slapolicies/{id}`**
  - **Descrição:** Atualiza uma política existente.
  - **Request Body:** (Mesma estrutura do `POST`)

- **`DELETE /slapolicies/{id}`**
  - **Descrição:** Deleta uma política.
  - **Response (204 No Content)**

---

### 8. Papéis (Roles)

**Controller:** `RoleController`

- **`GET /roles`**
  - **Descrição:** Lista todos os papéis.
  - **Response Body (200 OK):** `List<Role>`

- **`GET /roles/{id}`**
  - **Descrição:** Busca um papel por ID.
  - **Response Body (200 OK):** `Role`

- **`POST /roles`**
  - **Descrição:** Cria um novo papel.
  - **Request Body:**
    ```json
    {
      "role": "ROLE_ADMIN"
    }
    ```
  - **Response Body (200 OK):** `Role`

- **`PUT /roles/{id}`**
  - **Descrição:** Atualiza um papel.
  - **Request Body:** (Mesma estrutura do `POST`)

- **`DELETE /roles/{id}`**
  - **Descrição:** Deleta um papel.
  - **Response (204 No Content)**

---

### 9. Métricas

**Controller:** `MetricsController`

- **`GET /metrics/team/{teamId}`**
  - **Descrição:** Retorna métricas de performance para um time específico.
  - **Response Body (200 OK):**
    ```json
    {
      "resolvedTickets": 150,
      "slaMet": 145,
      "openTickets": 12
    }
    ```

- **`GET /metrics/employee/{employeeId}`**
  - **Descrição:** Retorna métricas de performance para um técnico específico.
  - **Response Body (200 OK):** (Mesma estrutura da métrica de time)
