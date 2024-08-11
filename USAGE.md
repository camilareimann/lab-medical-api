# Documentação para Utilização do Lab Medical API com Insomnia

## Introdução

O **Lab Medical API** oferece diversos endpoints para gerenciar informações de pacientes, consultas, exames e usuários. Esta documentação orienta sobre como utilizar esses endpoints através do Insomnia.

## Pré-requisitos

- **Insomnia** instalado em sua máquina. Você pode baixá-lo [aqui](https://insomnia.rest/download).
- O **Lab Medical API** deve estar em execução localmente.

## Configuração do Insomnia

1. **Abra o Insomnia**.
2. **Crie um novo workspace**:
   - Clique em "Create" e selecione "Workspace".
   - Dê um nome ao seu workspace, por exemplo, "Lab Medical API".

3. **Adicionar um novo Request**:
   - Clique no botão de "+" ao lado de "Requests".
   - Escolha "Request" e dê um nome ao seu request.

## Autenticação

Para interagir com a API, você precisa autenticar-se e obter um token JWT.
Ao rodar a aplicação pela primeira vez, um usuário padrão é criado com as seguintes credenciais:
```
username: admin
password: admin123
```

### Endpoint de Login

- **Método**: `POST`
- **URL**: `http://localhost:8080/login`

### Corpo da Requisição

**EXEMPLO DE REQUISIÇÃO NO INSOMNIA**

- Selecione o método POST.
- Insira a URL: http://localhost:8080/login.
- Vá para a aba "Body" e selecione "JSON".
- Cole o seguinte JSON:

```json
{
  "username": "admin",
  "password": "admin123"
}
```
- Clique em "Send".

### Resposta

Se as credenciais estiverem corretas, você receberá um token JWT no corpo da resposta

```json
{
  "token": "seu_token_jwt",
  "tempoExpiracao": 36000
}
```

- **Configurando o cabeçalho de autenticação**:
   - Clique em "Headers".
   - Adicione um novo cabeçalho com a chave `Authorization`
   - No valor, insira `Bearer seu_token_jwt`.
  
## Endpoints

A seguir, estão listados os endpoints disponíveis na API.

## Criar um novo usuario
- **Método**: `POST`
- **URL**: `http://localhost:8080/usuarios`
- **Autenticação**: Adicione o token JWT ao cabeçalho.
- **Corpo da Requisição**: Insira os dados do usuário no corpo da requisição.
- **Exemplo de Corpo da Requisição**:

```json
{
   "username": "joao",
   "email": "joao@example.com",
   "cpf": "000.000.000-00",
   "dataNascimento": "1944-03-21",
   "password": "senhaSegura",
   "perfil": ["ADMIN"]
}
```

## Criar um novo paciente
- **Método**: `POST`
- **URL**: `http://localhost:8080/pacientes`
- **Autenticação**: Adicione o token JWT ao cabeçalho.
- **Corpo da Requisição**: Insira os dados do paciente no corpo da requisição.
- **Exemplo de Corpo da Requisição**:

```json
  {
   "nomeCompleto": "Joao Pessoa",
   "genero": "Masculino",
   "dataNascimento": "1944-03-21",
   "cpf": "000.000.000-00",
   "rg": "MG-12.345.678",
   "estadoCivil": "Casado",
   "telefone": "(11) 9 8765-4321",
   "email": "joao.silva@example.com",
   "naturalidade": "Belo Horizonte",
   "contatoEmergencia": "(11) 9 8765-4322",
   "alergias": ["Poeira", "Camarão"],
   "cuidadosEspecificos": ["Evitar esforços físicos"],
   "convenio": "Unimed",
   "numeroConvenio": "123456789",
   "validadeConvenio": "2023-12-31",
   "endereco": {
      "cep": "30130-130",
      "cidade": "Belo Horizonte",
      "estado": "MG",
      "logradouro": "Rua dos Bobos",
      "numero": "10",
      "complemento": "Apto 101",
      "bairro": "Centro",
      "pontoDeReferencia": "Próximo à praça central"
   },
   "username": "joao"
}
```

### Listar todos os pacientes
- **Método**: `GET`
- **URL**: `http://localhost:8080/pacientes`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

### Buscar paciente por ID
- **Método**: `GET`
- **URL**: `http://localhost:8080/pacientes/{id}`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

### Atualizar um paciente
- **Método**: `PUT`
- **URL**: `http://localhost:8080/pacientes/{id}`
- **Autenticação**: Adicione o token JWT ao cabeçalho.
- **Corpo da Requisição**: Insira os dados do paciente no corpo da requisição.

### Deletar um paciente
- **Método**: `DELETE`
- **URL**: `http://localhost:8080/pacientes/{id}`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

## Consultas

### Criar uma nova consulta
- **Método**: `POST`
- **URL**: `http://localhost:8080/consultas`
- **Autenticação**: Adicione o token JWT ao cabeçalho.
- **Corpo da Requisição**: Insira os dados da consulta no corpo da requisição.
- **Exemplo de Corpo da Requisição**:

```json
{
   "motivo": "Dor no joelho",
   "dataConsulta": "2023-08-01",
   "horarioConsulta": "09:00:00",
   "descricaoProblema": "Paciente relatou dor no joelho constante há 3 dias.",
   "medicacaoReceitada": "Antiinflamatorio {% faker 'randomLocale' %}",
   "dosagemPrecaucao": "Aplicar creme a cada 4h",
   "idPaciente": 1
}
```

### Listar todas as consultas
- **Método**: `GET`
- **URL**: `http://localhost:8080/consultas`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

### Buscar consulta por ID
- **Método**: `GET`
- **URL**: `http://localhost:8080/consultas/{id}`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

### Atualizar uma consulta
- **Método**: `PUT`
- **URL**: `http://localhost:8080/consultas/{id}`
- **Autenticação**: Adicione o token JWT ao cabeçalho.
- **Corpo da Requisição**: Insira os dados da consulta no corpo da requisição.

### Deletar uma consulta
- **Método**: `DELETE`
- **URL**: `http://localhost:8080/consultas/{id}`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

## Exames

### Criar um novo exame
- **Método**: `POST`
- **URL**: `http://localhost:8080/exames`
- **Autenticação**: Adicione o token JWT ao cabeçalho.
- **Corpo da Requisição**: Insira os dados do exame no corpo da requisição.
- **Exemplo de Corpo da Requisição**:

```json
{
   "nome": "Hemograma Completo",
   "dataExame": "2023-08-05",
   "horarioExame": "10:30:00",
   "tipo": "Sangue",
   "laboratorio": "Laboratório ABC",
   "urlDocumento": "http://example.com/hemograma.pdf",
   "resultados": "Todos os resultados dentro da normalidade",
   "idPaciente": 1
}
```

### Listar todos os exames
- **Método**: `GET`
- **URL**: `http://localhost:8080/exames`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

### Buscar exame por ID
- **Método**: `GET`
- **URL**: `http://localhost:8080/exames/{id}`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

### Atualizar um exame
- **Método**: `PUT`
- **URL**: `http://localhost:8080/exames/{id}`
- **Autenticação**: Adicione o token JWT ao cabeçalho.
- **Corpo da Requisição**: Insira os dados do exame no corpo da requisição.

### Deletar um exame
- **Método**: `DELETE`
- **URL**: `http://localhost:8080/exames/{id}`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

## Prontuario

### Listar todos os prontuarios
- **Método**: `GET`
- **URL**: `http://localhost:8080/prontuarios`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

### Buscar prontuario por ID
- **Método**: `GET`
- **URL**: `http://localhost:8080/pacientes/{id}/prontuarios`

## Dashboard

### Obter informações do Dashboard
- **Método**: `GET`
- **URL**: `http://localhost:8080/dashboard`
- **Autenticação**: Adicione o token JWT ao cabeçalho.

### Resposta
Se as credenciais estiverem corretas, você receberá um JSON com as estatísticas de pacientes, consultas e exames.

```json
{
   "totalPacientes": 2,
   "totalConsultas": 3,
   "totalExames": 2
}
```

## Conclusão

Agora você está pronto para começar a interagir com a **Lab Medical API** através do Insomnia. Se precisar de mais informações, consulte a [documentação oficial](README.md).