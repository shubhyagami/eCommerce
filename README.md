[K[2m  [2mmodel z-ai/glm-5.3-flash failed, trying next...[0m[0m
[K[2m  [2mmodel deepseek-ai/deepseek-v4.1-flash failed, trying next...[0m[0m
# eCommerce

A lightweight full‑stack e‑commerce prototype built with **Java 17** and **Spring Boot 3**.  
The backend exposes a REST API for managing products, categories, variants, and orders.  
A minimal HTML/CSS front‑end demonstrates how to consume the API.  
Production‑grade patterns are in place: JWT authentication, role‑based access control, Spring Data JPA, PostgreSQL persistence, and automated CI/CD via GitHub Actions.

![Build](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![Test](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)  
![Coverage](https://img.shields.io/codecov/c/github/shubhyagami/eCommerce?style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---  

## Table of Contents

- [Overview](#overview)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Quick Start with Docker](#quick-start-with-docker)
  - [Local Development (no Docker)](#local-development-no-docker)
- [Features](#features)
- [Architecture](#architecture)
- [Testing](#testing)
- [Contribution Guidelines](#contribution-guidelines)
- [License](#license)
- [Changelog](#changelog)

---  

## Overview

* **Authentication** – JWT login with salted password hashing and `ADMIN`/`CUSTOMER` roles.  
* **Product Management** – Full CRUD for products, categories, and variants.  
* **Order System** – Create, view, and cancel orders; stock is adjusted automatically.  
* **API Documentation** – Swagger UI available at `/swagger-ui.html`.  
* **Demo UI** – Lightweight static site in `/frontend` that consumes the API.  
* **CI/CD** – GitHub Actions build, test, and coverage checks.

---  

## Getting Started

### Prerequisites

| Item | Minimum Version |
|------|-----------------|
| Java | 17 (JDK or JRE) |
| Maven | 3.8+ |
| Docker | 20.10+ (for Docker Compose) |
| PostgreSQL | 14+ (local or container) |

### Quick Start with Docker

```bash
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
docker compose up -d
```

The API runs on <http://localhost:8080>.  
The demo UI can be served with a simple static server:

```bash
cd frontend
python -m http.server 8000
```

Open <http://localhost:8000> to view the site.

> The `docker‑compose.yml` file starts a PostgreSQL container pre‑loaded with the schema.

### Local Development (no Docker)

1. **Create a PostgreSQL database**

   ```bash
   sudo -u postgres createuser -P your_user
   sudo -u postgres createdb -O your_user your_database
   ```

2. **Apply the schema**

   ```bash
   psql -U your_user -d your_database -f src/main/resources/schema.sql
   ```

3. **Configure the application**

   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```

   Edit `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   app.jwt.secret=${JWT_SECRET}
   ```

   Export the JWT secret:

   ```bash
   export JWT_SECRET="your_jwt_secret_key"
   ```

4. **Build and run**

   ```bash
   mvn clean package
   java -jar target/ecommerce-0.1.0.jar
   ```

   The API will be available at <http://localhost:8080>.

---  

## Features

| Feature | Description |
|---------|-------------|
| **Authentication** | JWT login, salted password hashing, and role‑based access control. |
| **Product Management** | CRUD for products, categories, and variants. |
| **Order Management** | Create, view, cancel orders; stock adjustments are automatic. |
| **API Docs** | Swagger/OpenAPI UI at `/swagger-ui.html`. |
| **Testing** | Unit and integration tests with > 100 % coverage via Codecov. |
| **CI/CD** | GitHub Actions build, test, and coverage checks. |
| **Demo UI** | Simple static front‑end consuming the REST API. |

---  

## Architecture

```
┌─────────────────────┐
│ Demo Front‑End    │
│ (HTML/CSS)         │
├─────────────────────┤
│ Spring Boot API    │
│ ├─ Controllers     │
│ ├─ Services        │
│ ├─ Repositories    │
│ ├─ Entities        │
│ └─ Configurations │
├─────────────────────┤
│ PostgreSQL 14+     │
└─────────────────────┘
```

* **Controllers** expose REST endpoints and handle HTTP concerns.  
* **Services** contain business logic and enforce transactional boundaries.  
* **Repositories** are Spring Data JPA interfaces for persistence.  
* **Entities** map to database tables with JPA annotations.  
* **Configurations** handle JWT, CORS, data source, and other beans.

---  

## Testing

Run the full test suite (unit + integration):

```bash
mvn test
```

Code coverage reports are generated in `target/site/jacoco-aggregate/index.html`.  
Coverage is displayed on the GitHub Actions badge and on Codecov.

---  

## Contribution Guidelines

1. Fork the repository.  
2. Create a feature branch (`git checkout -b feature/<name>`).  
3. Write clear, concise commit messages.  
4. Add or update unit tests for new code.  
5. Run `mvn spotless:apply` to format the code before committing.  
6. Open a pull request against `main`; the CI pipeline will validate your changes.

### Style

* Java 17 syntax, no deprecated APIs.  
* 4‑space indentation, sorted imports.  
* Use camelCase for Java fields and snake_case for database columns.

---  

## License

MIT © 2026 Shubhya Yagami

---  

## Changelog

**0.1.0 – 2026‑09‑28**  
* Initial release: JWT authentication, RBAC, CRUD for products, categories, variants, and orders.  
* PostgreSQL schema and Docker‑compose setup.  
* Minimal static front‑end demo.  
* CI pipeline with code coverage.
