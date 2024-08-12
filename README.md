# Lab Medical API

## Descrição

O **Lab Medical API** é um projeto desenvolvido como parte da avaliação do módulo 2 do curso FullStack oferecido pelo SESI/SENAI. Esta API é responsável por gerenciar as informações de pacientes, consultas, exames e usuários, oferecendo funcionalidade de autenticação e autorização.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.2**
- **Spring Data JPA**
- **Spring Security**
- **PostgreSQL**
- **JSON Web Tokens (JWT)**

## Funcionalidades

- **Gestão de Pacientes**: Criar, atualizar, buscar e deletar informações de pacientes.
- **Gestão de Consultas**: Criar, atualizar, buscar e deletar consultas.
- **Gestão de Exames**: Criar, atualizar, buscar e deletar exames.
- **Sistema de Autenticação**: Login de usuários com verificação de credenciais e geração de token JWT.
- **Dashboard**: Visualização de estatísticas sobre pacientes, consultas e exames.

## Estrutura do Projeto

```plaintext
LABMedical/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── LABMedical/
│   │   │           ├── controller/
│   │   │           ├── dto/
│   │   │           ├── exception/
│   │   │           ├── model/
│   │   │           ├── repository/
│   │   │           ├── security/
│   │   │           ├── service/
│   │   │           └── util/
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── public-key.pem
│   │       └── private-key.pem
└── pom.xml
```

## Configurações

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/LABMedical.git
   cd LABMedical
    ```
2. **Configurar o banco de dados**:

   Crie um banco de dados chamado labMedical.

   Atualize suas credenciais no arquivo application.properties.
   ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/labMedical
    spring.datasource.username=seuUsuario
    spring.datasource.password=suaSenha
   ```
3. **Compile e execute o projeto**:
    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```
## Endpoints

### Autenticação
POST /login: Realiza o login e retorna um token JWT.

### Pacientes
GET /pacientes: Lista todos os pacientes.
POST /pacientes: Cria um novo paciente.
GET /pacientes/{id}: Busca um paciente pelo ID.
PUT /pacientes/{id}: Atualiza um paciente existente.
DELETE /pacientes/{id}: Deleta um paciente.

### Consultas
GET /consultas: Lista todas as consultas.
POST /consultas: Cria uma nova consulta.
GET /consultas/{id}: Busca uma consulta pelo ID.
PUT /consultas/{id}: Atualiza uma consulta existente.
DELETE /consultas/{id}: Deleta uma consulta.

### Exames
GET /exames: Lista todos os exames.
POST /exames: Cria um novo exame.
GET /exames/{id}: Busca um exame pelo ID.
PUT /exames/{id}: Atualiza um exame existente.
DELETE /exames/{id}: Deleta um exame.

### Dashboard
GET /dashboard: Retorna estatísticas de pacientes, consultas e exames.

## Licença
Este projeto está licenciado sob a MIT License.

## Autor
Desenvolvido por Camila Reimann em agosto 2024.

## Documentação de Uso com Insomnia

Para instruções detalhadas sobre como usar a API com o Insomnia, consulte [USAGE.md](USAGE.md).