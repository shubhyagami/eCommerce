[K[2m  [2mmodel z-ai/glm-5.3-flash failed, trying next...[0m[0m
[K[2m  [2mmodel deepseek-ai/deepseek-v4.1-flash failed, trying next...[0m[0m
# eCommerce

A lightweight full‑stack e‑commerce prototype built with **Java 17** and **Spring Boot 3**.  
The backend exposes a REST API for managing products, categories, variants, and orders, while a minimal HTML/CSS front‑end demonstrates how to consume the API.  
Production‑grade practices are in place: JWT authentication, role‑based access control, Spring Data JPA, PostgreSQL persistence, and automated CI/CD via GitHub Actions.

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

The project implements:

- **JWT + RBAC** – Secure authentication with `ADMIN` and `CUSTOMER` roles.
- **Product CRUD** – Full lifecycle management of products, categories, and variants.
- **Order Management** – Create, view, and cancel orders with stock handling.
- **Swagger UI** – Interactive API docs at `/swagger-ui.html`.
- **Automated CI/CD** – Build, test, and coverage checks on GitHub Actions.
- **Demo UI** – Lightweight HTML/CSS front‑end that talks to the API.

---

## Getting Started

```bash
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
```

### Quick Start with Docker

```bash
docker compose up -d
```

The API will be accessible at <http://localhost:8080>.  
The demo UI can be served with a simple static server:

```bash
cd frontend
python -m http.server 8000
```

Open <http://localhost:8000> to see the UI.

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
| **Authentication** | JWT based login, salted password hashing, role‑based access. |
| **Product Management** | CRUD for products, categories, and variants. |
| **Order System** | Create, read, cancel orders; automatic stock adjustments. |
| **Documentation** | Swagger/OpenAPI UI. |
| **Testing** | Unit and integration tests; coverage reported by Codecov. |
| **CI/CD** | GitHub Actions perform build, test, and coverage checks. |
| **Demo UI** | Simple static site that consumes the API. |

---

## Architecture

```
┌─────────────────────┐
│  Demo Front‑End (HTML/CSS)   │
├─────────────────────┤
│  Spring Boot API          │
│   ├─ Controllers         │
│   ├─ Services            │
│   ├─ Repositories        │
│   ├─ Entities            │
│   └─ Configurations     │
├─────────────────────┤
│  PostgreSQL 14+             │
└─────────────────────┘
```

- **Controllers** expose REST endpoints and handle HTTP concerns.
- **Services** contain business logic and enforce transactional boundaries.
- **Repositories** are Spring Data JPA interfaces for persistence.
- **Entities** map to database tables with JPA annotations.
- **Configurations** set up JWT, CORS, data source, and other beans.

---

## Testing

Run the full test suite (unit + integration):

```bash
mvn test
```

Code coverage reports are generated in `target/site/jacoco-aggregate/index.html`.  
Coverage is also displayed on the GitHub Actions badge and via Codecov.

---

## Contribution Guidelines

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/<name>`).
3. Write clear, concise commit messages.
4. Add or update unit tests for new code.
5. Run `mvn spotless:apply` to format code before committing.
6. Open a pull request against `main`; a CI run will automatically validate your changes.

**Style**

- Java 17 syntax, no deprecated APIs.
- 4‑space indentation, sorted imports.
- Use camelCase for Java fields and snake_case for database columns.

---

## License

MIT © 2026 Shubhya Yagami

---

## Changelog

**0.1.0** – 2026‑09‑28  
- Initial release with JWT authentication and RBAC.  
- CRUD for products, categories, variants, and orders.  
- PostgreSQL schema and Docker‑compose setup.  
- Minimal static front‑end demo.  
- CI pipeline with code coverage.
