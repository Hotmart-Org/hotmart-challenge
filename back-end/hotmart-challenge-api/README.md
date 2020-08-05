# Hotmart Challenge
Backend do marketplace de produtos.

## Tecnologias Utilizadas
* Java 8
* Spring Boot
* Hibernate
* Spring Data JPA
* Spring Security
*	Maven
* H2
* Flyway
* Junit

## Banco de dados

A aplicação utiliza o banco de dados em memória H2.

As configurações do banco estão no arquivo:

```
/hotmart-challenge-api/src/main/resources/application.properties
```
Endereço para acessar console do H2

```
http://<host>:<porta>/h2-console/

Ex:

http://localhost:8080/h2-console/

Usuário: sa
Senha: sa
```
Os scripts para criação do banco e carga inicial dos dados estão na pasta:

```
/hotmart-challenge-api/src/main/resources/db/migration
```

## Como executar a aplicação

1. Clonar o repositório
```
https://github.com/thiagolvaz/hotmart-challenge.git
```

2. Instalar as dependências do projeto
```
mvn clean install
````

3. Gerar o pacote
```
mvn package
```

4. Executar
```
java –jar target/hotmart-challenge-api-0.0.1-SNAPSHOT.jar
```

Como alternativa, você pode executar o aplicativo sem empacotá-lo usando:
```
mvn spring-boot: run
```

Para executar pelo Eclipse basta executar a classe:
```
HotmartChallengeApiApplication.java
```

## Detalhamento da Aplicação

A aplicação possui os seguintes recursos:

* Operações para CRUD(create, read, update, delete) de produtos.
*	Serviço para ranquear os produtos.
*	Serviço para buscar os produtos ranqueados.
*	Suporte a paginação na listagem dos produtos.
*	Scheduler para executar o ranqueamento diariamente.
*	Auditoria.
*	Segurança nos endpoints que persistem dados(autenticação em memória).

O ranqueamento diário dos produtos é executado através de um job que utiliza Cron Expression.

O job está programado para executar todo dia às 01:00h am e sua configuração pode ser encontrada no arquivo:
```
/hotmart-challenge-api/src/main/java/com/hotmart/challenge/web/scheduler/ScheduledTasks.java
```

## Endpoints da Aplicação

A aplicação começará a ser executada em http://localhost:8080.

Os endpoints que persistem dados possuem segurança do tipo `Basic Auth`.

A autenticação utiliza usuário e senha em memória.

Para incluir, alterar, excluir e ranquear produtos, é necessário informar no request o usuário e senha abaixo.
```
Usuário: hotmart
Senha: h0tm@rt
```

O arquivo `Hotmart_Challenge.postman_collection.json` com os requests da api está localizado na raiz do projeto e pode ser importado no Postman.

* GET
```
Listar produtos: /api/v1/produtos/?{page}&{size}

	Ex: /api/v1/produtos/?page=0&size=50

Buscar pelo nome: /api/v1/produtos/nome/?{page}&{size}&{nome}

	Ex: /api/v1/produtos/nome/?page=0&size=50&nome=Alpha

Buscar pelo id: /api/v1/produtos/id/{id}

	Ex: /api/v1/produtos/id/1

Ranquear produtos: /api/v1/ranquear

```

* POST
```
Cadastrar produto: /api/v1/produtos

Body JSON ex:
{
    "nome": "Smartphone",
    "descricao": "Iphone 20",
    "dataCriacao": "2020-07-30T17:20:11.000",
    "categoria": {
        "id": 6
    }
}
```

* PUT
```
Atualizar produtos produto: /api/v1/produtos/{id}
	
Ex: /api/v1/produtos/1

Body JSON ex:
{
    "nome": "Tablet",
    "descricao": "Samsung SX",
    "dataCriacao": "2020-07-30T17:20:11",
    "categoria": {
        "id": 6
    }
}
```

* DELETE
```
Excluir produto: /api/v1/produtos/{id}

Ex: /api/v1/produtos/890
```
