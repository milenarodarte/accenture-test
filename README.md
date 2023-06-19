# Back-end API Companies-Suppliers

## Pontos a se destacar:
Essa APi foi feita sem nenhuma experiência prévia na linguagem Java, sendo, além do meu primeiro contato com uma linguagem fortemente tipada, o primeiro trabalho com programação POO. Foi desenvolvida em 3 dias e meio. 

## Linguagem de programação escolhida: Java com framework Springboot. 
BIBLIOTECA JPA para auxiliar no acesso e manipulação do banco de dados.

SPRING MVC para auxiliar com solicitações e respostas HTTP.

SPRING SERIALIZR para construir e inicilizar o projeto com Spring boot.

MAVEN para compilar e gerir dependências.

## Banco de dados
PostgreSQL
- _modelagem do banco de dados: https://app.diagrams.net/_


# COMO INICIALIZAR O PROJETO

1. clone o repositório em sua máquina
2. verifique se já há instalado o ambiente Java necessário (JDK v20 e Maven).
```bash 
❯ mvn -version
Apache Maven 3.9.2 (c9616018c7a021c1c39be70fb2843d6f5f9b8a1c)
Maven home: /Users/username/Downloads/apache-maven-3.9.2
Java version: 20.0.1, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-20.jdk/Contents/Home
Default locale: en_BR, platform encoding: UTF-8
OS name: "mac os x", version: "13.2.1", arch: "aarch64", family: "mac"
```
```bash
❯ java -version
java version "20.0.1" 2023-04-18
Java(TM) SE Runtime Environment (build 20.0.1+9-29)
Java HotSpot(TM) 64-Bit Server VM (build 20.0.1+9-29, mixed mode, sharing)
```
3. importar o projeto para sua IDE de preferência. No desenvolvimento desse projeto, foi utilizado o Intellij IDEA
4. em `/backend/companyAndSuppliers/src/main/resources` crie um arquivo chamado `application.properties` com as variáveis de ambiente, seguindo o exemplo:
```
server.port=<SERVER_PORT>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

logging.level.org.springframework.jdbc.datasource.init.ScriptUtils=debug

spring.jpa.properties.hibernate.globally_quoted_identifier=true
spring.jpa.properties.hibernate.globally_quoted_identifiers_skip_column_definitions = true

spring.datasource.url=jdbc:postgresql://localhost:<POSTGRES_PORT>/<database_name>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
logging.level.root=INFO
```
4. na pasta `/backend/companyAndSuppliers`, rode o comando para abrir o servidor na porta configurada (a padrão é 2000): 
```bash
❯ mvn spring-boot:run
```

# ROTAS (http://localhost:<SERVER_PORT>)
## COMPANIES 

### GET (/companies)
retorno de todas as companies cadastradas no banco de dados. 

### GET (/companies/{id})
retorno da company buscada por seu ID.

### GET (/companies/cnpj/{cnpj}) 
retorno da company buscada por seu CNPJ

### GET (companies/business_name/{businessName})
retorno das companies por seu business_name 

### POST (/companies) 
criaçao da company através do corpo completo de requisição: 
```json
{ "business_name": "string", "cnpj": "string", "cep": "string" }
```

### PUT (companies/{id})
update da company através do envio do seu id e corpo completo de requisição: 
```json
{ "business_name": "string", "cnpj": "string", "cep": "string" }
```

### DELETE (/companies/{id})
deleçao a company e seus respectivos relacionamentos

### POST (companies/{companyId}/supplier/{supplierId})
relaciona um supplier a uma company através dos seus ids passados no parâmetro. 

### DELETE (companies/{companyId}/supplier/{supplierId})
deleta o relacionamento de um supplier com uma company


## SUPPLIERS

### GET (/suppliers)
retorno de todas os suppliers cadastradas no banco de dados. 

### GET (/suppliers/{id})
retorno do supplier buscada por seu ID.

### GET (/suppliers/name/{name})
retorno dos suppliers por seu name 

### GET (/suppliers/cpfcnpj/{cpfcnpj}) 
retorno do supplier buscado por seu CNPJ ou CPF

### POST (/suppliers) 
criaçao do supplier através do envio do seu id e corpo completo de requisição: 
```json
  {	
    "name": "string",	
    "cpfCnpj": "string", 
    "email": "string", 
    "cep": "string", 
    "rg": "string", 
    "birthdate": "YYYY-MM-DD"
  }
```
- _RG e Birthdate apenas para pessoa física._

### PUT (/suppliers/{id})
update do supplier através do envio do seu id e corpo completo de requisição: 
```json
  {	
    "name": "string",	
    "cpfCnpj": "string", 
    "email": "string", 
    "cep": "string", 
    "rg": "string", 
    "birthdate": "YYYY-MM-DD"
  }
```
- _RG e Birthdate apenas para pessoa física._

### DELETE (suppliers/{Id})
deleta um supplier e seus respectivos relacionamentos

## COMPANY_SUPPLIER (/companies_suppliers)


### GET (/companies_suppliers)
retorno de todops os relacionamentos cadastradas no banco de dados. 

### GET (/companies_suppliers/company_id/{companyId})
retorno de todops os relacionamentos que tiverem o id da company_id passada. 

### GET (/companies_suppliers/supplier_id/{supplierId})
retorno de todops os relacionamentos que tiverem o id do supplier_id passada. 

### GET (/companies_suppliers/company_id/{companyId}/supplier_id/{supplierId})
retorno de todops o relacionamento que corresponder a company_id com o supplier_id passado. 


