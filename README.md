API - Bank Java Spring
---
Projeto com Java, Spring, PostgreSQL, além de docker e flyway.
- Docker - para contenização
- Flyway - para controlar das migrations de banco

Subindo dependências da aplicação
---
Banco de dados (PostgreSQL) e interface gráfica (adminer) para visualização dos dados do banco.   

    docker-compose up

Acessando o Adminer para verificar os dados - http://localhost:5433

*Configurações:*

    banco=postgres

    port=5432
	
    server=database

	usuario=bank

	senha=bank

*Observação:*
Ativei o módulo PgCrypto no PostgreSQL para os criptografar dados PCI. Deixei a implementação comentada.

Iniciando aplicação via terminal
---
Para rodar a aplicação via terminal setando um profile.

    ./gradlew bootRun -Dspring.profiles.active=local

Build do projeto
---
Fazendo o build da aplicação. 

    ./gradlew clean build

Criar imagem docker do projeto
---
No diretório raiz do projeto.

    docker build .


Acessando a documentação no Swagger
---
    http://localhost:8080/swagger-ui.html

