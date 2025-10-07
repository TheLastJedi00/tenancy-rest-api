# ThinkDesk Tenancy REST API

## Visão Geral

O **ThinkDesk** é uma API REST multi-inquilino (multi-tenant) projetada para gerenciar um sistema de suporte e atendimento, como um helpdesk ou ITSM (IT Service Management). A API permite que diferentes "inquilinos" (tenants) operem de forma isolada, cada um com seus próprios usuários, técnicos, tickets e políticas de serviço.

A aplicação foi construída utilizando Java e Spring Boot, seguindo os princípios da **Arquitetura Limpa (Clean Architecture)** para garantir uma separação clara de responsabilidades, alta testabilidade e manutenibilidade.

A estrutura do projeto é uma implementação prática dos princípios da Arquitetura Limpa (Clean Architecture) e da Arquitetura Hexagonal (Ports and Adapters). O objetivo é isolar o núcleo de negócio (Domain e Application) das dependências externas (Infra). As interfaces definidas no Domínio atuam como "Portas", enquanto as implementações na camada de Infraestrutura (como repositórios JPA e controllers REST) funcionam como "Adaptadores"

---

## Arquitetura

O projeto é dividido em três camadas principais, refletindo os princípios da Arquitetura Limpa:

- **`/src/main/java/ThinkDesk/Domain`**: O coração da aplicação. Esta camada contém os modelos de negócio (Entidades), as regras de negócio centrais e as interfaces dos repositórios. Não possui dependências de frameworks externos como Spring ou JPA.

- **`/src/main/java/ThinkDesk/Application`**: A camada de casos de uso (Use Cases). Ela orquestra o fluxo de dados entre a camada de interface e a de domínio. Contém os `Services` da aplicação, os `Controllers` que definem os endpoints da API e os `DTOs` (Data Transfer Objects) para a comunicação.

- **`/src/main/java/ThinkDesk/Infra`**: A camada mais externa, contendo os detalhes de implementação. Aqui ficam as implementações dos repositórios (usando Spring Data JPA), a configuração de segurança (Spring Security, JWT), clientes para serviços externos (como o Gemini API Client) e outras configurações de infraestrutura.

O fluxo de uma requisição segue o seguinte padrão:

`Controller (Application) -> Service (Application) -> Entidade/Domínio (Domain) <- Repositório (Infra)`

---

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**: Framework principal para a construção da aplicação.
- **Spring Data JPA**: Para persistência de dados e implementação dos repositórios.
- **Hibernate**: Implementação da JPA para o mapeamento objeto-relacional.
- **MySQL**: Banco de dados relacional.
- **Spring Security**: Para autenticação e autorização.
- **JWT (JSON Web Tokens)**: Para a segurança de endpoints stateless.
- **Lombok**: Para reduzir código boilerplate (getters, setters, construtores).
- **Maven/Gradle**: Para gerenciamento de dependências e build.

---

## Pré-requisitos e Configuração

1.  **Java Development Kit (JDK)**: Versão 17 ou superior.
2.  **Maven ou Gradle**: Instalado e configurado no seu ambiente.
3.  **MySQL**: Uma instância do MySQL Server rodando.

### Como Executar

1.  **Clone o repositório:**
    ```bash
    git clone <url-do-repositorio>
    cd tenancy-rest-api
    ```

2.  **Configure o Banco de Dados:**
    - Crie um banco de dados no seu MySQL chamado `multi_tenancy`.
    - Abra o arquivo `src/main/resources/application.properties`.
    - Altere as propriedades `spring.datasource.username` e `spring.datasource.password` com as suas credenciais do MySQL.

3.  **Execute a Aplicação:**
    - Você pode executar a aplicação pela sua IDE (IntelliJ, Eclipse) ou via linha de comando:
    ```bash
    # Usando Maven
    ./mvnw spring-boot:run
    ```

4.  A API estará disponível em `http://localhost:8080`.

---

## Endpoints da API

A autenticação é necessária para a maioria dos endpoints e é feita através de um Bearer Token JWT no header `Authorization`.

### Autenticação

| Método | Path       | Descrição                                         |
|--------|------------|-----------------------------------------------------|
| `POST` | `/login`   | Autentica um `User` ou `Technician` e retorna um token JWT. |

### Tenants

- **Path Base:** `/tenants`

| Método | Path       | Descrição                 |
|--------|------------|---------------------------|
| `GET`  | `/`        | Lista todos os tenants.   |
| `GET`  | `/{id}`    | Busca um tenant por ID.   |
| `POST` | `/`        | Cria um novo tenant.      |
| `PUT`  | `/{id}`    | Atualiza um tenant.       |

### Usuários (Users)

- **Path Base:** `/users`

| Método | Path       | Descrição                 |
|--------|------------|---------------------------|
| `GET`  | `/`        | Lista todos os usuários.  |
| `POST` | `/`        | Cria um novo usuário.     |

### Técnicos (Technicians)

- **Path Base:** `/technicians`

| Método | Path       | Descrição                   |
|--------|------------|-----------------------------|
| `GET`  | `/`        | Lista todos os técnicos.    |
| `POST` | `/`        | Cria um novo técnico.       |

### Tickets

- **Path Base:** `/tickets`

| Método | Path       | Descrição                 |
|--------|------------|---------------------------|
| `GET`  | `/`        | Lista todos os tickets.   |
| `GET`  | `/{id}`    | Busca um ticket por ID.   |
| `POST` | `/`        | Cria um novo ticket.      |
| `PUT`  | `/{id}`    | Atualiza um ticket.       |

### Políticas de SLA (SLA Policies)

- **Path Base:** `/slas`

| Método | Path       | Descrição                     |
|--------|------------|-------------------------------|
| `GET`  | `/`        | Lista todas as políticas.     |
| `POST` | `/`        | Cria uma nova política de SLA.|

### Métricas

- **Path Base:** `/metrics`

| Método | Path             | Descrição                                      |
|--------|------------------|------------------------------------------------|
| `GET`  | `/team/{id}`     | Retorna métricas de performance para um time.  |
| `GET`  | `/employee/{id}` | Retorna métricas de performance para um técnico. |

