# PetShop API

Uma API simples que simula o cadastro de animais em um PetShop, desenvolvida como parte do curso Start.

## Funcionalidades

- **Operações CRUD**: Gerencie pets e seus serviços.
- **Integração com Banco de Dados**: Utiliza PostgreSQL como banco de dados.
- **Documentação OpenAPI**: Swagger UI integrado para documentação da API.
- **Spring Boot**: Construída com Spring Boot para desenvolvimento rápido.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.4**
- **PostgreSQL**
- **Lombok**
- **SpringDoc OpenAPI**

## Endpoints

### Pets

- **GET /pets**  
  Retorna todos os pets cadastrados no banco de dados.

- **GET /pets/{id}**  
  Retorna um pet pelo seu ID.

- **POST /pets**  
  Cadastra um novo pet no banco de dados.  
  **Exemplo de Corpo da Requisição**:  
  ```json
  {
    "name": "Buddy",
    "birthDate": "01-01-2020",
    "gender": "M",
    "species": "Cachorro",
    "breed": "Golden Retriever",
    "size": "L",
    "serviceType": "GROOMING"
  }
  ```

- **PUT /pets/{id}**  
  Atualiza os dados de um pet existente.  
  **Corpo da Requisição**: Igual ao POST.

- **DELETE /pets/{id}**  
  Exclui um pet pelo seu ID.

## Instruções de Configuração

### Pré-requisitos

- Java 17
- Maven
- Docker (opcional, para rodar o PostgreSQL)

### Executando a Aplicação

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-repositorio/petshop-api.git
   cd petshop-api
   ```

2. Configure o banco de dados:
   - Atualize o arquivo `application.properties` em `src/main/resources` com as credenciais do seu PostgreSQL:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/db
     spring.datasource.username=seu_usuario
     spring.datasource.password=sua_senha
     spring.jpa.hibernate.ddl-auto=update
     ```

3. Inicie a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse a documentação da API em:  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Executando com Docker

1. Construa e inicie os containers:
   ```bash
   docker-compose up --build
   ```

2. Acesse a API em:  
   [http://localhost:8080](http://localhost:8080)

## Estrutura do Projeto

- `src/main/java/br/start/petshop`  
  Contém o código principal da aplicação, incluindo controllers, services, entities e repositories.

- `src/main/resources`  
  Contém arquivos de configuração, como `application.properties`.

- `docker-compose.yml`  
  Define os serviços Docker para a aplicação e o PostgreSQL.

## Licença

Este projeto está licenciado sob a Licença Apache 2.0. Consulte o arquivo [LICENSE](http://www.apache.org/licenses/LICENSE-2.0) para mais detalhes.