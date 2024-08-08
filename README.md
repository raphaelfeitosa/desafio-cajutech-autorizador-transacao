# Desafio da empresa Caju. 

## Projeto Autorizador de Transações de cartão de credito. 

## Funcionalidades
- **L1. Autorizador Simples - desafio OK**
- **L2. Autorizador com Fallback - desafio OK**
- **L3. Dependente do Comerciante - desafio OK**
- **L4. Questão Aberta - desafio OK**

## Utilização do Redis para Lock de Transação

Para a solução do L4 foi utilizado o Redis, um banco de dados em memória, para realizar o lock da transação.

### O que é Lock de Transação?
Lock de transação é uma técnica usada para garantir que apenas uma operação de transação ocorra por vez em um recurso específico. Isso garante a consistência dos dados, especialmente em sistemas distribuídos.

### Por que Usar Redis para Lock de Transação?
Redis é uma escolha popular para implementar lock de transação devido às suas operações rápidas e atomicidade de comandos, o que é crucial para garantir que os locks sejam adquiridos e liberados de maneira eficiente e confiável.

## O que há neste documento

- [Executando todos os testes](https://github.com/raphaelfeitosa/desafio-cajutech-autorizador-transacao#executando-todos-os-testes)
- [Executando o projeto com Docker](https://github.com/raphaelfeitosa/desafio-cajutech-autorizador-transacao#executando-o-projeto-com-docker)
- [Executando o projeto localmente](https://github.com/raphaelfeitosa/desafio-cajutech-autorizador-transacao#executando-o-projeto-localmente)
- [Tecnologias](https://github.com/raphaelfeitosa/desafio-cajutech-autorizador-transacao#tecnologias)
- [Recursos](https://github.com/raphaelfeitosa/desafio-cajutech-autorizador-transacao#recursos-da-api-v1)
- [Swagger](https://github.com/raphaelfeitosa/desafio-cajutech-autorizador-transacao#swagger)
- [Autor](https://github.com/raphaelfeitosa/desafio-cajutech-autorizador-transacao#autor)


## Executando todos os testes

No terminal, navegue até a pasta raiz do projeto e execute

```shell
./mvnw test
```
no Windows

```shell
mvnw.cmd test
```

## Executando o projeto com Docker

É necessario ter o docker instalado! No terminal, navegue até a pasta raiz do projeto e execute

```shell
docker-compose up --build
```

## Executando o projeto localmente

Para que não seja necessário instalar nada em sua máquina, a aplicação esta configurada para salvar os dados no banco de dados em mémoria.

No terminal, navegue até a pasta raiz do projeto e execute

```shell
./mvnw clean install 
```

no Windows

```shell
mvnw.cmd clean install 
```

Após a conclusão, execute

```shell
./mvnw spring-boot:run
```

no Windows

```shell
mvnw.cmd spring-boot:run
```
## Arquitetura do Pojeto

Foi utilizado a arquitetura hexagonal, também conhecida como arquitetura de portas e adaptadores (Ports and Adapters Architecture). . Seu principal objetivo é criar sistemas que sejam fáceis de manter, testar, e que permitam a integração com diferentes tipos de interfaces de entrada (usuários, APIs, filas de mensagens) e de saída (bancos de dados, serviços externos) sem impactar a lógica central do sistema.

## Tecnologias

As seguintes tecnologias foram usadas na construção do projeto:

- **Spring Boot**
- **Kotlin**
- **DynamoDB**
- **Redis**
- **Swagger**
- **Junit 5**
- **Docker**
- **LocalStack**

## Recursos da API v1
Recursos disponíveis para acesso via api: `http://localhost:8081/api/v1`

- ### Rescurso [/merchants], Methods: POST
  ```
  POST: /merchants: para cadastrar ou atualizar um merchant
  ```

- ### Rescurso [/accounts], Methods: GET, POST, PATCH
  ```
  GET: /accounts/{documentNumber}: para listar o account id
  POST: /accounts: para cadastrar um account
  PATCH: /accounts/{accountId}/balance: para adicionar saldo na conta
  ```

- ### Responses
  | Código | Descrição |
  |---|---|
  | `200` | Requisição executada com sucesso (Success).|
  | `201` | Requisição cadastrada com sucesso (Success).|
  | `400` | Erros de validação ou cadastro existente (Bad Request).|
  | `404` | Registro pesquisado não encontrado (Not Found).|

### MERCHANT
**POST:** `/merchants` com *body*:

- Request (application/json)
    ```json
    {
        "name": "merchant",
        "categories": ["FOOD"]
    }
    ```

- Response 201 (Created)
    ```json
    {
	      "merchantId": "dc3f982d-de6f-453e-a024-782c57cae73b"
    }
    ```

### ACCOUNT
**POST:** `/accounts` com *body*:

- Request (application/json)
    ```json
    { 	
        "name": "Jhon Doe", 	
        "documentNumber": "01234567890"
    }
    ```

- Response 201 (Created)
    ```json
    {
	      "accountId": "dc3f982d-de6f-453e-a024-782c57cae73b"
    }
    ```

- Response 422 (Bad Request) - Cadastro existente
    ```json
    {
      "timestamp": "2024-08-07T21:45:16Z",
      "code": "UNPROCESSABLE_ENTITY",
      "message": "Document number already registered",
      "path": "uri=/api/v1/accounts"
    }
    ```

- Response 400 (Bad Request) - Erros na validação
     ```json
   {
         "timestamp": "2024-08-07T21:46:29Z",
         "code": "BAD_REQUEST",
         "message": "Method argument not valid",
         "path": "uri=/api/v1/accounts",
         "errors": {
             "name must not be blank": "name must not be blank",
             "documentNumber invalid": "documentNumber invalid",
             "name must be between 2 and 50 characters": "name must be between 2 and 50 characters"
         }
   }
  ```

### Consultar accountId por documentNumber
**GET** `/accounts/{documentNumber}` vai retornar:

- Response 200 (application/json)
    ```json
     {
        "accountId": "5dab011c-3001-4c76-8d53-c581d6e21788"
     }
    ```

- Response 404 (Not Found)
    ```json
    {
      "timestamp": "2024-08-07T21:49:12Z",
      "code": "NOT_FOUND",
      "message": "Resource not found",
      "path": "uri=/api/v1/accounts/0123456789"
    }
    ```

### Adicionar saldos nos beneficios por categia
**PATCH** `/accounts/{accountId}/balance` com *body*:

- Request (application/json)
    ```json
    { 	
      "cash": 3000.0,
      "food": 1000.0,
      "meal": 1500.0
    }
    ```

- Response 200 (application/json)
    ```json
   {
	"message": "Balance update with success!"
   }
    ```
  ### MERCHANT
**POST:** `/transactions` com *body*:

- Request (application/json)
    ```json
   {
      "accountId": "7379e2ce-6187-4d08-ae03-5683d342a8d2",
      "merchant": "merchant",
      "amount": 700.00,
      "mcc": "5412"
   }
    ```

- Response 200 (Ok) - Transação APROVADA
  ```json
   {
	"code": "00"
   }
    ```
- Response 200 (Ok) - Transação REJEITADA
    ```json
   {
    	"code": "51"
   }
    ```
- Response 200 (Ok) - Problema na Transação
  ```json
  {
  	"code": "07"
  }
    ```

- Response 400 (Bad Request) - Erros na validação
     ```json
  {
      "timestamp": "2024-08-07T22:31:48Z",
      "code": "BAD_REQUEST",
      "message": "Method argument not valid",
      "path": "uri=/api/v1/transactions",
      "errors": {
          "accountId must be an UUID": "accountId must be an UUID",
          "mcc must contain 4 numbers": "mcc must contain 4 numbers",
          "must be greater than or equal to 0.01": "must be greater than or equal to 0.01",
          "merchant cannot be blank": "merchant cannot be blank"
       }
  }
  ```
## Swagger

`http://localhost:8080/api/swagger-ui/index.html`

<p align="center" style="display: flex; align-items: flex-start; justify-content: center;">
  <img alt="Vendas" title="#Home" src="https://github.com/raphaelfeitosa/desafio-cajutech-autorizador-transacao/blob/main/assets/swagger-trasaction-authorizer.png?raw=true"/>
</p>

## Autor

<a href="https://github.com/raphaelfeitosa">
 <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/raphaelfeitosa" width="100px;" alt=""/>

<sub><b>Raphael Feitosa</b></sub></a> <a href="https://github.com/raphaelfeitosa/" title="Rocketseat">🚀</a>
<br />

[<img src="https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" />](https://www.linkedin.com/in/raphael-feitosa/) <a href="mailto:raphaelcs2@gmail.com"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"/></a>
<br />

[⬆ Voltar ao topo](#grade-curricular)<br>
