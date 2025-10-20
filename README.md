# ThinkDesk Tenancy REST API

## Visão Geral

O **ThinkDesk** é uma API REST multi-inquilino (multi-tenant) projetada para gerenciar um sistema de suporte e atendimento (ITSM). A API permite que diferentes "inquilinos" (tenants) operem de forma isolada, cada um com seus próprios usuários, técnicos, tickets e políticas de serviço.

A aplicação foi construída utilizando Java e Spring Boot, seguindo os princípios da **Arquitetura Limpa (Clean Architecture)** para garantir uma separação clara de responsabilidades, alta testabilidade e manutenibilidade.

---

## Arquitetura

O projeto é dividido em três camadas principais: `Domain`, `Application`, e `Infra`.

- **`Domain`**: O coração da aplicação. Contém as entidades de negócio (`Ticket`, `User`, `Tenant`), as regras de negócio e as interfaces dos repositórios. É totalmente independente de frameworks.
- **`Application`**: Orquestra o fluxo de dados. Contém os `Controllers` que expõem a API REST e os `DTOs` que definem os contratos de dados (JSONs).
- **`Infra`**: A camada externa. Contém as implementações concretas (como repositórios JPA), a configuração de segurança (Spring Security), e o tratamento de exceções.

---

## Guia de Endpoints da API

A autenticação é necessária para a maioria dos endpoints e é feita através de um Bearer Token JWT no header `Authorization`.

### 1. Autenticação (`LoginController`)

- **`POST /login`**
  - **Descrição:** Autentica um usuário ou técnico e retorna um token JWT.
  - **Request Body:**
    ```json
    {
      "login": "tecnico@example.com",
      "password": "password123"
    }
    ```
  - **Response (200 OK):**
    ```json
    {
      "token": "ey...[jwt_token]..."
    }
    ```

---

### 2. Tenants (`TenantController`)

- **`POST /tenants`**
  - **Descrição:** Cria um novo tenant (empresa).
  - **Request Body:**
    ```json
    {
      "tradingName": "Empresa Exemplo SA",
      "legalName": "Empresa Exemplo LTDA",
      "taxID": "12.345.678/0001-99",
      "settings": "{\"theme\":\"dark\"}"
    }
    ```
  - **Response (201 Created):** Retorna o objeto do tenant criado.

- **`GET /tenants`**
  - **Descrição:** Lista todos os tenants.
  - **Response (200 OK):**
    ```json
    [
      {
          "id": 1,
          "tradingName": "Empresa Exemplo SA",
          "legalName": "Empresa Exemplo LTDA",
          "taxID": "12345678000199",
          "createdAt": "2024-10-28T10:00:00",
          "active": true,
          "settings": "{\"theme\":\"dark\"}"
      }
    ]
    ```

- **`GET /tenants/{id}`**
  - **Descrição:** Busca um tenant específico por ID.
  - **Response (200 OK):** Retorna o objeto do tenant encontrado.

- **`PUT /tenants/{id}`**
  - **Descrição:** Atualiza um tenant existente.
  - **Request Body:** (Mesma estrutura do `POST`)
  - **Response (200 OK):** Retorna o objeto do tenant atualizado.

- **`DELETE /tenants/{id}`**
  - **Descrição:** Deleta um tenant.
  - **Response (204 No Content)**

---

### 3. Usuários (`UserController`)

- **`POST /users`**
  - **Descrição:** Cria um novo usuário (solicitante) associado a um tenant.
  - **Request Body:**
    ```json
    {
      "name": "Usuário Final",
      "email": "usuario@exemplo.com",
      "password": "password123",
      "position": "Analista de Marketing",
      "tenantId": 1
    }
    ```
  - **Response (201 Created):**
    ```json
    {
        "id": 1,
        "name": "Usuário Final",
        "email": "usuario@exemplo.com",
        "position": "Analista de Marketing",
        "active": true,
        "tenantId": 1
    }
    ```

- **`GET /users`**
  - **Descrição:** Lista todos os usuários.
  - **Response (200 OK):** Retorna uma lista de objetos `UserResponseDto`.

- **`GET /users/{id}`**
  - **Descrição:** Busca um usuário por ID.
  - **Response (200 OK):** Retorna um único objeto `UserResponseDto`.

- **`PUT /users/{id}`**
  - **Descrição:** Atualiza um usuário.
  - **Request Body:** (Mesma estrutura do `POST`, exceto `password` que é opcional)
  - **Response (200 OK):** Retorna o objeto `UserResponseDto` atualizado.

- **`DELETE /users/{id}`**
  - **Descrição:** Deleta um usuário.
  - **Response (204 No Content)**

---

### 4. Técnicos (`TechnicianController`)

- **`POST /technicians`**
  - **Descrição:** Cria um novo técnico associado a um tenant.
  - **Request Body:**
    ```json
    {
      "name": "Técnico N1",
      "email": "tecnico@exemplo.com",
      "password": "password123",
      "level": "L1"
    }
    ```
  - **Response (201 Created):** Retorna o objeto do técnico criado.

- **`GET /technicians`**
  - **Descrição:** Lista todos os técnicos.
  - **Response (200 OK):** Retorna uma lista de objetos `Technician`.

- **`GET /technicians/{id}`**
  - **Descrição:** Busca um técnico por ID.
  - **Response (200 OK):** Retorna um único objeto `Technician`.

- **`PUT /technicians/{id}`**
  - **Descrição:** Atualiza um técnico.
  - **Request Body:**
    ```json
    {
      "name": "Técnico N1",
      "email": "tecnico@exemplo.com",
      "level": "L2"
    }
    ```
  - **Response (200 OK):** Retorna o objeto `Technician` atualizado.

- **`DELETE /technicians/{id}`**
  - **Descrição:** Deleta um técnico.
  - **Response (204 No Content)**

---

### 5. Políticas de SLA (`SlaPolicyController`)

- **`POST /slapolicies`**
  - **Descrição:** Cria uma nova política de SLA. Note que a categoria e a prioridade podem ser criadas dinamicamente aqui.
  - **Request Body:**
    ```json
    {
      "name": "SLA Padrão - TI",
      "responseTimeMinutes": 120,
      "resolutionTimeMinutes": 480,
      "operationalHoursOnly": true,
      "isActive": true,
      "categoryDto": { "name": "Infraestrutura", "description": "Problemas de infraestrutura" },
      "tenantId": 1,
      "priorityDto": { "name": "Média" }
    }
    ```
  - **Response (201 Created):**
    ```json
    {
        "id": 1,
        "name": "SLA Padrão - TI",
        "responseTimeMinutes": 120,
        "resolutionTimeMinutes": 480,
        "categoryDto": { "id": 1, "name": "Infraestrutura", "description": "Problemas de infraestrutura" },
        "priorityDto": { "id": 1, "name": "Média" }
    }
    ```

- **`GET /slapolicies`**
  - **Descrição:** Lista todas as políticas de SLA.
  - **Response (200 OK):** Retorna uma lista de `SlaPolicyResponseDTO`.

- **`GET /slapolicies/{id}`**
  - **Descrição:** Busca uma política por ID.
  - **Response (200 OK):** Retorna um único `SlaPolicyResponseDTO`.

- **`PUT /slapolicies/{id}`**
  - **Descrição:** Atualiza uma política.
  - **Request Body:** (Mesma estrutura do `POST`)
  - **Response (200 OK):** Retorna o `SlaPolicyResponseDTO` atualizado.

- **`DELETE /slapolicies/{id}`**
  - **Descrição:** Deleta uma política.
  - **Response (204 No Content)**

---

### 6. Tickets (`TicketController`)

- **`POST /tickets`**
  - **Descrição:** Cria um novo ticket de suporte.
  - **Request Body:**
    ```json
    {
      "title": "Impressora não funciona",
      "description": "A impressora do 2º andar parou de funcionar.",
      "ticketType": "INCIDENT",
      "category": 1,
      "technician": 1,
      "tenant": 1,
      "requester": 2,
      "priority": 3
    }
    ```
  - **Response (201 Created):**
    ```json
    {
        "id": 101,
        "title": "Impressora não funciona",
        "description": "A impressora do 2º andar parou de funcionar.",
        "status": "OPEN",
        "resolutionDueDate": "2024-10-28T18:00:00",
        "ticketType": "INCIDENT",
        "category": { "id": 1, "name": "Hardware" },
        "technician": { "id": 1, "name": "Técnico N1" },
        "tenant": { "id": 1, "tradingName": "Empresa Exemplo SA" },
        "requester": { "id": 2, "name": "Usuário Final" },
        "priority": { "id": 3, "name": "Média" }
    }
    ```

- **`GET /tickets`**
  - **Descrição:** Lista todos os tickets de forma paginada.
  - **Response (200 OK):** Retorna um objeto `Page` contendo uma lista de `Ticket`.

- **`GET /tickets/{id}`**
  - **Descrição:** Busca um ticket por ID.
  - **Response (200 OK):** Retorna um único objeto `Ticket`.

- **`PUT /tickets/{id}`**
  - **Descrição:** Atualiza um ticket.
  - **Request Body:**
     ```json
    {
      "title": "Impressora não funciona",
      "description": "A impressora do 2º andar parou de funcionar.",
      "ticketType": "INCIDENT",
      "category": 1,
      "technician": 1,
      "tenant": 1,
      "requester": 2,
      "priority": 3
    }
    ```
  - **Response (200 OK):** Retorna o objeto `Ticket` atualizado.

- **`DELETE /tickets/{id}`**
  - **Descrição:** Deleta um ticket.
  - **Response (204 No Content)**

---

### 7. Histórico de Tickets (`TicketLogController`)

- **`POST /ticketlog`**
  - **Descrição:** Adiciona uma nova entrada de log a um ticket existente.
  - **Request Body:**
    ```json
    {
      "content": "Técnico verificou o problema e escalou para o N2.",
      "isPrivate": false,
      "ticket_id": 101
    }
    ```
  - **Response (201 Created):**
    ```json
    {
      "id": 50,
      "content": "Técnico verificou o problema e escalou para o N2.",
      "createdAt": "2024-10-28T14:30:00",
      "isPrivate": false,
      "ticket_id": 101,
      "authorName": "Técnico N1"
    }
    ```

- **`GET /ticketlog/ticket/{ticketId}`**
  - **Descrição:** Lista todos os logs de um ticket específico.
  - **Response (200 OK):** Retorna uma lista de objetos `TicketLog`.

- **`PUT /ticketlog/{id}`**
  - **Descrição:** Atualiza uma entrada de log.
  - **Request Body:** (Mesma estrutura do `POST`)
  - **Response (200 OK):** Retorna o objeto `TicketLog` atualizado.

- **`DELETE /ticketlog/{id}`**
  - **Descrição:** Deleta uma entrada de log.
  - **Response (204 No Content)**

---

### 8. Papéis (`RoleController`)

- **`POST /roles`**
  - **Descrição:** Cria um novo papel (role) no sistema. (Uso geralmente administrativo).
  - **Request Body:**
    ```json
    {
      "name": "ROLE_ADMIN"
    }
    ```
  - **Response (201 Created):**
    ```json
    {
      "id": 1,
      "name": "ROLE_ADMIN"
    }
    ```

- **`GET /roles`**
  - **Descrição:** Lista todos os papéis.
  - **Response (200 OK):** Retorna uma lista de objetos `Role`.

---

### 9. Métricas (`MetricsController`)

- **`GET /metrics/team/{teamId}`**
  - **Descrição:** Retorna métricas de performance para um time específico.
  - **Response (200 OK):**
    ```json
    {
      "resolvedTickets": 150,
      "slaMet": 145,
      "openTickets": 12,
      "averageResolutionTimeMinutes": 210
    }
    ```

- **`GET /metrics/employee/{employeeId}`**
  - **Descrição:** Retorna métricas de performance para um técnico específico.
  - **Response (200 OK):**
    ```json
    {
      "resolvedTickets": 30,
      "slaMet": 28,
      "openTickets": 5,
      "averageResolutionTimeMinutes": 180
    }
    ```
