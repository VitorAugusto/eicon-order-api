# Desafio Eicon - Programador Java

## 👾 REST API

### Software desenvolvido em Java - Spring Boot para a recepção e processamento de pedidos em formato XML e JSON.

## 🧰 Scripts e componentes
### O projeto possui os seguintes componentes:  

1) Collection do Postman com pedidos prontos em formato XML/JSON e chamadas de consulta <br>
    `eicon-order-api/collections`
2) Script de criação das tabelas <br>
   `eicon-order-api/scripts`

## 🛠️ Como instalar e Utilizar

### 1. Pre-requisitos<br>
* Java 8
* MySQL

### 2. Clone o Projeto

### 3. Rodar script de criação das tabelas <br>
`scripts/tabelas.sql`

### 4. Configurar no arquivo `application.yml` na seção <br>
`  datasource:
   url: jdbc:mysql://localhost:3306/order_api
   username: root
   password: root
` as credenciais de acesso ao banco

### 5. Rodar a aplicação

## 📄 Swagger
    http://localhost:8080/swagger-ui/index.html