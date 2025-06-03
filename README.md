PetShop API

 API REST desenvolvida em Java com Spring Boot para gerenciamento de pets, clientes e agendamentos em um PetShop. Permite cadastrar, listar, buscar, atualizar e remover animais, clientes e agendamentos, além de consultar pets por espécie e agendamentos por cliente. Documentação interativa disponível via Swagger/OpenAPI.

 ---

 ## Funcionalidades

 - Pets

 - Cadastro, listagem, busca por ID, busca por espécie, atualização e remoção de pets.

 - Clientes

 - Cadastro, listagem, busca por ID, busca por e-mail, atualização e remoção de clientes.

 - Agendamentos

 - Cadastro, listagem, busca por cliente, atualização e remoção de agendamentos.

 - Validações

 - Validação de campos obrigatórios, formatos de data e integridade referencial.

 - Tratamento de erros

 - Respostas padronizadas para erros de validação, não encontrado e exceções genéricas.

 - Documentação automática

 - Swagger/OpenAPI disponível em /swagger-ui.html.

 ---

 ## Principais Endpoints

 ### Pets

 - GET /pets — Lista todos os pets.

 - GET /pets/{id} — Busca pet por ID.

 - GET /pets/species/{species} — Busca pets por espécie.

 - POST /pets — Cadastra novo pet.

 - PUT /pets/{id} — Atualiza pet existente.

 - DELETE /pets/{id} — Remove pet.

 ### Clientes

 - GET /clients — Lista todos os clientes.

 - GET /clients/{id} — Busca cliente por ID.

 - GET /clients/email/{email} — Busca cliente por e-mail.

 - POST /clients — Cadastra novo cliente.

 - PUT /clients/{id} — Atualiza cliente existente.

 - DELETE /clients/{id} — Remove cliente.

 ### Agendamentos

 - GET /appointments — Lista todos os agendamentos.

 - GET /appointments/client/{clientId} — Lista agendamentos de um cliente.

 - POST /appointments — Cadastra novo agendamento.

 - PUT /appointments/{id} — Atualiza agendamento.

 - DELETE /appointments/{id} — Remove agendamento.

 ---

 ## Exemplos de Requisição

 ### Cadastro de Pet

 json

 {
  
     "name": "Rex",

   "birthDate": "01-01-2020",

   "gender": "M",

   "species": "Cachorro",

   "breed": "Labrador",

   "size": "L",

   "serviceType": "GROOMING",

   "clientId": 1

 }

 

 ### Cadastro de Cliente

 json

 {
  
     "name": "Maria Silva",

   "email": "maria@email.com",

   "phone": "11999999999",

   "petIds": [1, 2],

   "appointmentIds": [1]

 }

 

 ### Cadastro de Agendamento

 json

 {
  
     "clientId": 1,

   "petId": 1,

   "appointmentDateTime": "10-06-2025 14:00",

   "serviceType": "BATHING",

   "notes": "Banho especial"

 }

 

 ---

 ## Variáveis de Ambiente

 Configure as seguintes variáveis de ambiente para conexão com o banco de dados:

 - SPRING_DATASOURCE_URL (ex: jdbc:postgresql://localhost:5432/petshop)

 - SPRING_DATASOURCE_USERNAME

 - SPRING_DATASOURCE_PASSWORD

 - SPRING_JPA_HIBERNATE_DDL_AUTO (ex: update)

 - SPRING_PROFILES_ACTIVE (ex: dev)

 No Docker Compose, já estão configuradas para uso local.

 ---

 ## Como Executar

 ### Com Docker

 1. Execute:

 bash

    docker-compose up --build

    

 2. Acesse a API em http://localhost:8080

 3. Acesse a documentação Swagger em http://localhost:8080/swagger-ui.html

 ### Manualmente

 1. Configure as variáveis de ambiente ou o arquivo application.properties.

 2. Execute:

 bash

    ./mvnw spring-boot:run

    

 ---

 ## Estrutura do Projeto

 - src/main/java/br/start/petshop/controllers — Controllers REST

 - src/main/java/br/start/petshop/services — Regras de negócio

 - src/main/java/br/start/petshop/entities — Entidades JPA

 - src/main/java/br/start/petshop/DTOs — Data Transfer Objects

 - src/main/java/br/start/petshop/enums — Enums de domínio

 - src/main/java/br/start/petshop/repositories — Repositórios JPA

 - src/main/java/br/start/petshop/exceptions — Tratamento de exceções

 - docker-compose.yml — Orquestração de containers

 - pom.xml — Dependências Maven

 ---

 ## Licença

 Projeto licenciado sob Apache 2.0. Veja o arquivo LICENSE.

json

 {
  
     "name": "Rex",

      "birthDate": "01-01-2020",

      "gender": "M",

      "species": "Cachorro",

      "breed": "Labrador",

      "size": "L",

      "serviceType": "GROOMING",

      "clientId": 1

 }

 

 ### Cadastro de Cliente

 json

 {
  
     "name": "Maria Silva",

      "email": "maria@email.com",

      "phone": "11999999999",

      "petIds": [1, 2],

      "appointmentIds": [1]

 }

 

 ### Cadastro de Agendamento

 json

 {
  
     "clientId": 1,

      "petId": 1,

      "appointmentDateTime": "10-06-2025 14:00",

      "serviceType": "BATHING",

      "notes": "Banho especial"

 }

 

 ---

 ## Variáveis de Ambiente

 Configure as seguintes variáveis de ambiente para conexão com o banco de dados:

 - SPRING_DATASOURCE_URL (ex: jdbc:postgresql://localhost:5432/petshop)

 - SPRING_DATASOURCE_USERNAME

 - SPRING_DATASOURCE_PASSWORD

 - SPRING_JPA_HIBERNATE_DDL_AUTO (ex: update)

 - SPRING_PROFILES_ACTIVE (ex: dev)

 No Docker Compose, já estão configuradas para uso local.

 ---

 ## Como Executar

 ### Com Docker

 1. Execute:

 bash

    docker-compose up --build

    

 2. Acesse a API em http://localhost:8080

 3. Acesse a documentação Swagger em http://localhost:8080/swagger-ui.html

 ### Manualmente

 1. Configure as variáveis de ambiente ou o arquivo application.properties.

 2. Execute:

 bash

    ./mvnw spring-boot:run

    

 ---

 ## Estrutura do Projeto

 - src/main/java/br/start/petshop/controllers — Controllers REST

 - src/main/java/br/start/petshop/services — Regras de negócio

 - src/main/java/br/start/petshop/entities — Entidades JPA

 - src/main/java/br/start/petshop/DTOs — Data Transfer Objects

 - src/main/java/br/start/petshop/enums — Enums de domínio

 - src/main/java/br/start/petshop/repositories — Repositórios JPA

 - src/main/java/br/start/petshop/exceptions — Tratamento de exceções

 - docker-compose.yml — Orquestração de containers

 - pom.xml — Dependências Maven

 ---

 ## Licença

 Projeto licenciado sob Apache 2.0. Veja o arquivo LICENSE.

