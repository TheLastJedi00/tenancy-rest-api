# API REST de Zoológico

Uma API REST completa para o gerenciamento de um zoológico, desenvolvida com Java e Spring Boot.

## Visão Geral

Esta API fornece uma interface para realizar operações de CRUD (Criar, Ler, Atualizar e Deletar) nas principais entidades de um zoológico, como Animais, Cuidadores, Habitats e Veterinários. Ela foi projetada seguindo as melhores práticas de desenvolvimento de APIs REST, incluindo o uso de DTOs, tratamento de exceções e segurança baseada em tokens.

## Tecnologias Utilizadas

-   **Java 17+**
-   **Spring Boot 3+**
-   **Spring Data JPA (Hibernate)**
-   **Spring Security**
-   **Maven / Gradle**
-   **Spring Mail**
-   **Banco de Dados (ex: H2, PostgreSQL)**
-   **Lombok**

## Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone <URL_DO_REPOSITORIO>
    ```
2.  **Configure o banco de dados:**
    -   As configurações de banco de dados estão no arquivo `src/main/resources/application.properties`. Por padrão, ele pode estar configurado para um banco em memória como o H2.

3.  **Execute a aplicação:**
    -   **Com Maven:**
        ```bash
        mvn spring-boot:run
        ```
    -   **Com Gradle:**
        ```bash
        ./gradlew bootRun
        ```
4.  **Acesse a API:**
    -   A API estará disponível em `http://localhost:8080`.

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas para garantir a separação de responsabilidades e a manutenibilidade.

```
com.example.zoo
└── Application
    ├── controllers      // Camada de API (Endpoints REST)
    └── dtos             // Data Transfer Objects (para requisições e respostas)
└── Domain
    ├── reposiories     // Camada de acesso a dados (Spring Data JPA)
    ├── model           // Entidades JPA (representação do banco de dados)
    ├── service         // Camada de lógica de negócio
└── Infra
    ├── security        // configurações da camada de segurança
    ├── mapper          // Mapeadores de dto para entidades
    └── config          // Configurações de segurança (Spring Security, JWT)
```

## Segurança

A API utiliza **JSON Web Tokens (JWT)** para autenticação e autorização, garantindo que apenas usuários autenticados possam acessar os endpoints.

-   **Autenticação:** Para acessar os endpoints protegidos, é necessário enviar um token JWT no cabeçalho `Authorization` de cada requisição.
    ```
    Authorization: Bearer <seu-token-jwt>
    ```
-   **Endpoint de Autenticação:**
    -   `POST /auth/login`: Envie credenciais (`username` e `password`) para receber um token JWT.

-   **Autorização (Roles):** O acesso a determinados endpoints pode ser restrito por papéis (roles).
    -   `ROLE_USER`: Acesso geral para consulta.
    -   `ROLE_ADMIN`: Acesso total, incluindo criação, atualização e exclusão de recursos.

## 📝 Endpoints da API

A seguir estão os endpoints disponíveis, seguindo os padrões REST.

### Animais
-   **Base Path:** `/animais`

| Método  | Endpoint                               | Descrição                                    | Resposta de Sucesso |
| :------ | :------------------------------------- | :------------------------------------------- | :------------------ |
| `GET`   | `/`                                    | Retorna uma lista de animais.                | `200 OK`            |
| `GET`   | `?nome={nome}&especie={espécie}`       | Filtra animais por nome e/ou espécie.        | `200 OK`            |
| `GET`   | `/{id}`                                | Retorna um animal específico pelo ID.        | `200 OK`            |
| `POST`  | `/`                                    | Cria um novo animal.                         | `201 Created`       |
| `PUT`   | `/{id}`                                | Atualiza um animal existente.                | `200 OK`            |
| `DELETE`| `/{id}`                                | Deleta um animal pelo ID.                    | `204 No Content`    |

### Cuidadores
-   **Base Path:** `/cuidadores`

| Método  | Endpoint                               | Descrição                                    | Resposta de Sucesso |
| :------ | :------------------------------------- | :------------------------------------------- | :------------------ |
| `GET`   | `/`                                    | Retorna uma lista de cuidadores.             | `200 OK`            |
| `GET`   | `?especialidade={esp}&turno={turno}`   | Filtra cuidadores por especialidade e/ou turno. | `200 OK`            |
| `GET`   | `/{id}`                                | Retorna um cuidador específico pelo ID.      | `200 OK`            |
| `POST`  | `/`                                    | Cria um novo cuidador.                       | `201 Created`       |
| `PUT`   | `/{id}`                                | Atualiza um cuidador existente.              | `200 OK`            |
| `DELETE`| `/{id}`                                | Deleta um cuidador pelo ID.                  | `204 No Content`    |

### Habitats
-   **Base Path:** `/habitats`

| Método  | Endpoint                               | Descrição                                    | Resposta de Sucesso |
| :------ | :------------------------------------- | :------------------------------------------- | :------------------ |
| `GET`   | `/`                                    | Retorna uma lista de habitats.               | `200 OK`            |
| `GET`   | `?tipo={tipo}`                         | Filtra habitats por tipo.                    | `200 OK`            |
| `GET`   | `/{id}`                                | Retorna um habitat específico pelo ID.       | `200 OK`            |
| `POST`  | `/`                                    | Cria um novo habitat.                        | `201 Created`       |
| `PUT`   | `/{id}`                                | Atualiza um habitat existente.               | `200 OK`            |
| `DELETE`| `/{id}`                                | Deleta um habitat pelo ID.                   | `204 No Content`    |

### Veterinários
-   **Base Path:** `/veterinarios`

| Método  | Endpoint                               | Descrição                                    | Resposta de Sucesso |
| :------ | :------------------------------------- | :------------------------------------------- | :------------------ |
| `GET`   | `/`                                    | Retorna uma lista de veterinários.           | `200 OK`            |
| `GET`   | `?especialidade={especialidade}`       | Filtra veterinários pela especialidade.      | `200 OK`            |
| `GET`   | `/{id}`                                | Retorna um veterinário específico pelo ID.   | `200 OK`            |
| `POST`  | `/`                                    | Cria um novo veterinário.                    | `201 Created`       |
| `PUT`   | `/{id}`                                | Atualiza um veterinário existente.           | `200 OK`            |
| `DELETE`| `/{id}`                                | Deleta um veterinário pelo ID.               | `204 No Content`    |

## ⚙️ Regras de Negócio

A camada de serviço (`@Service`) implementa as seguintes regras de negócio para manter a integridade dos dados:

-   **Animais:**
    -   Um animal deve ser associado a um `habitatId` e `cuidadorId` existentes.
    -   Não é possível adicionar um animal a um habitat que já atingiu sua capacidade máxima.
-   **Cuidadores:**
    -   O nome do cuidador é obrigatório.
    -   Ao ser criado, um e-mail de boas-vindas é enviado para o cuidador.
-   **Habitats:**
    -   O nome e o tipo do habitat são obrigatórios.
    -   A capacidade máxima deve ser um número positivo.
-   **Veterinários:**
    -   O `crvm` (Conselho Regional de Medicina Veterinária) deve ser único para cada veterinário.

## Data Transfer Objects (DTOs)

Utilizamos DTOs para desacoplar a camada da API da camada de modelo de dados e para expor apenas as informações necessárias.

### `AnimalRequestDTO`
```json
{
  "nome": "Simba",
  "especie": "Leão",
  "idade": 10,
  "habitatId": 1,
  "cuidadorId": 1
}
```

### `CuidadorRequestDTO`
```json
{
  "nome": "João Silva",
  "especialidade": "Felinos",
  "turno": "Manhã",
  "email": "joao.silva@example.com"
}
```

### `HabitatRequestDTO`
```json
{
  "nome": "Savana Africana",
  "capacidadeMaxima": 15,
  "tipo": "Terrestre"
}
```

### `VeterinarioRequestDTO`
```json
{
  "nome": "Dra. Ana Souza",
  "crvm": "CRMV-SP 12345",
  "especialidade": "Animais Selvagens"
}
```

## Tratamento de Erros

A API utiliza códigos de status HTTP padrão para indicar o sucesso ou falha de uma requisição.

| Código | Status                 | Descrição                                                              |
| :----- | :--------------------- | :--------------------------------------------------------------------- |
| `200`  | `OK`                   | A requisição foi bem-sucedida (usado em `GET`, `PUT`).                  |
| `201`  | `Created`              | O recurso foi criado com sucesso (usado em `POST`).                    |
| `204`  | `No Content`           | A requisição foi bem-sucedida, mas não há conteúdo para retornar (usado em `DELETE`). |
| `400`  | `Bad Request`          | A requisição é inválida (ex: corpo malformado, dados faltando).        |
| `401`  | `Unauthorized`         | Autenticação falhou ou não foi fornecida (token JWT inválido ou ausente). |
| `403`  | `Forbidden`            | O usuário autenticado não tem permissão para acessar o recurso.        |
| `404`  | `Not Found`            | O recurso solicitado não foi encontrado.                               |
| `409`  | `Conflict`             | A requisição conflita com o estado atual do servidor (ex: `crvm` duplicado). |
| `500`  | `Internal Server Error`| Ocorreu um erro inesperado no servidor.                                |
