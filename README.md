API - Bank Java Spring
---
Projeto com Java, Spring, PostgreSQL, Lombok, Flyway e Docker.
- Docker - para contenização
- Flyway - para controlar das migrations de banco

*Observação:*
Ativado o módulo PgCrypto no PostgreSQL para os criptografar dados PCI. Implementação do uso de criptografia na aplicação esta comentada.


1 - Build e startup automatizado
---
Depois de clonar o repositório. Utilize o comando abaixo para provisionar e subir todo o ambiente necessário para testar a API localmente.

Processo todo automatizado para build do projeto, build da imagem em container docker e inicialização da aplicação com suas dependências.

*Depois de clonar o projeto, executar na pasta raiz da aplicação*

    make start

*Parando ambiente e destruindo os containers*

    make stop

Acessando a documentação no Swagger
---
    http://localhost:8080/swagger-ui.html

Acessando o Dados do Bando de dados
---

Acessando o Adminer para verificar os dados - http://localhost:5433

*Configurações:*

    banco=postgres

    port=5432
	
    server=database

	usuario=bank

	senha=bank

2 - Caso não possível utilizar o comando make
---

Build do aplicação
---
Fazendo o build da aplicação.

    ./gradlew clean build


Criar imagem docker do projeto
---
Para criar a imagem do projeto, basta fazer o build rodando o comando já listado acima e depois executar o comando abaixo no diretório raiz do projeto.

    docker build -t bank-java-spring:latest .


Subindo dependências da aplicação
---
Banco de dados (PostgreSQL) e interface gráfica (adminer) para visualização dos dados do banco.   

    docker-compose up

