# 📍 GPS Points of Interest API

[![Java 21](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/) [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)](https://spring.io/projects/spring-boot) [![Maven](https://img.shields.io/badge/Maven-3.x-red)](https://maven.apache.org/) [![H2](https://img.shields.io/badge/Database-H2-orange)](https://www.h2database.com/)

Uma API REST simples para gerenciar **Pontos de Interesse (POI)**, calcular distâncias e filtrar locais próximos com base em coordenadas `x`, `y` e raio máximo (`dmax`).  
Construída com **Java 21**, **Spring Boot**, **Spring Data JPA** e **H2 Database** em memória.

---

## 📖 Sumário

- [🏷️ Descrição](#️-descrição)
- [✨ Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias](#️-tecnologias)
- [🔧 Pré-requisitos](#️-pré-requisitos)
- [🚀 Getting Started](#-getting-started)
    - [Clone o repositório](#clone-o-repositório)
    - [Configuração (opcional)](#configuração-opcional)
    - [Build & Run](#build--run)
- [📡 Endpoints da API](#-endpoints-da-api)
    - [POST /pontos-de-interesse](#post-pontos-de-interesse)
    - [GET /listar-pontos-de-interesse](#get-listar-pontos-de-interesse)
    - [GET /listar-pontos-proximos](#get-listar-pontos-proximos)
- [📦 Data Model](#-data-model)
- [⚠️ Tratamento de Erros](#️-tratamento-de-erros)
- [🧪 Testes](#-testes)
- [🖥️ H2 Console](#️-h2-console)
- [🚧 Melhoria Futuras](#-melhoria-futuras)
- [📄 Licença](#-licença)

---

## 🏷️ Descrição

Este serviço expõe uma coleção de operações para:

1. **Criar** pontos de interesse com coordenadas `x` e `y`.
2. **Listar** todos os pontos cadastrados.
3. **Filtrar** e retornar apenas os pontos dentro de um raio (`dmax`) ao redor de uma coordenada de referência.

Internamente, a API utiliza:
- Spring Data JPA para persistência.
- H2 em memória para simplicidade de setup.
- Cálculo de distância Euclidiana para filtragem fina.

---

## ✨ Funcionalidades

- CRUD mínimo: **C**reate + **R**ead.
- Filtro espacial por distância.
- DTO para recepção de dados no POST.
- Respostas padronizadas com `ResponseEntity`.

---

## 🛠️ Tecnologias

| Camada            | Tecnologia            |
|-------------------|-----------------------|
| Linguagem         | Java 21               |
| Framework         | Spring Boot 3.x       |
| Persistência      | Spring Data JPA       |
| Banco de Dados    | H2 in-memory          |
| Build & Dependency| Maven 3.x             |

---

## 🔧 Pré-requisitos

- Java 21 JDK
- Maven 3.x
- IDE de sua preferência (IntelliJ IDEA, VS Code…)

---

## 🚀 Getting Started

### Clone o repositório

```bash
  git clone https://github.com/tobiascamylo/gps.git
  cd gps
```
## Configuração (opcional)
 - A configuração padrão usa H2 in-memory. Para customizar, edite src/main/resources/application.properties:
```properties
  spring.datasource.url=jdbc:h2:mem:gps
  spring.datasource.username=sa
  spring.datasource.password=
  spring.jpa.hibernate.ddl-auto=update
  spring.h2.console.enabled=true
```
## Build & Run
  - Compile e instale dependências
```bash
  mvn clean install
```
  - Execute a aplicação
```bash
  mvn spring-boot:run
```
  - Acesse a API em http://localhost:8080

## 📡 Endpoints da API
### POST /pontos-de-interesse
- **Descrição**: Cria um novo ponto de interesse.
- **Request Body**:
```json
{
  "nome": "Nome do Ponto",
  "x": 10.0,
  "y": 20.0
}
```
- **Response**:
 - 200 OK
```
### GET /listar-pontos-de-interesse
- **Descrição**: Lista todos os pontos de interesse cadastrados.
- **Response**:
```json
[
  {
    "id": 1,
    "nome": "Nome do Ponto",
    "x": 10.0,
    "y": 20.0
  },
  ...
]
``` 
### GET /listar-pontos-proximos
- **Descrição**: Lista pontos de interesse próximos a uma coordenada (x, y) dentro de um raio (dmax).
- **Request Parameters**:
```json
{
  "x": 10.0,
  "y": 20.0,
  "dmax": 5.0
}
```
- **Response**:
```json
[
  {
    "id": 1,
    "nome": "Nome do Ponto",
    "x": 10.0,
    "y": 20.0
  }
]
```

