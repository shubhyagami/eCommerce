[K[2m  [2mmodel z-ai/glm-5.3-flash failed, trying next...[0m[0m
[K[2m  [2mmodel deepseek-ai/deepseek-v4.1-flash failed, trying next...[0m[0m
# eCommerce

A lightweight, full‑stack e‑commerce prototype built with **Java 17** and **Spring Boot 3**.  
The backend exposes a REST API for managing products, categories, variants, and orders.  
A minimal HTML/CSS front‑end demonstrates how to consume the API.

It follows production‑grade practices: JWT authentication, role‑based access control, Spring Data JPA, PostgreSQL persistence, and automated CI/CD via GitHub Actions.

![Build](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![Test](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)  
![Coverage](https://img.shields.io/codecov/c/github/shubhyagami/eCommerce?style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Local Development (no Docker)](#local-development-no-docker)
- [Features](#features)
- [Architecture](#architecture)
- [Development Guidelines](#development-guidelines)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Prerequisites

- **Java 17** (JDK and `java` command)
- **Maven 3.8+** (`mvn`)
- **PostgreSQL 14+** (for the main database)
- **Docker** + **Docker Compose** (optional, for the quick start)
- Optional: **Python 3.8+** (to serve the static UI with `http.server`)

---

## Getting Started

Clone the repository and start the API with a local PostgreSQL database using Docker Compose:

```bash
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
docker compose up -d
```

The API will be available at <http://localhost:8080>.

> **Tip:** The static front‑end lives in the `frontend` folder. It can be served by any static file server. For a quick demo:

```bash
cd frontend
python -m http.server 8000
```

Open <http://localhost:8000> in a browser to see the demo UI.

---

## Local Development (no Docker)

1. **Create PostgreSQL user and database**

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

   Edit `application.properties` with your credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   app.jwt.secret=${JWT_SECRET}
   ```

   Set the environment variable for the JWT secret:

   ```bash
   export JWT_SECRET="your_jwt_secret_key"
   ```

4. **Build and run**

   ```bash
   mvn clean package
   java -jar target/ecommerce-0.1.0.jar
   ```

The API will be reachable at <http://localhost:8080>.

---

## Features

- **JWT + RBAC** – Secure authentication, salted password hashing, `ADMIN` / `CUSTOMER` roles.
- **Product CRUD** – Create, read, update, delete products, categories, and variants.
- **Order Management** – Create, view, and cancel orders with variant stock handling.
- **Swagger UI** – API documentation available at `http://localhost:8080/swagger-ui.html`.
- **Integration Tests** – Test coverage for core logic and security.
- **CI / CD** – Automated build, test, and code‑coverage checks on GitHub Actions.
- **Minimal Front‑End** – A quick HTML/CSS demo that consumes the API.

---

## Architecture

```
┌─────────────────────┐
│  Front‑End (HTML/CSS)  │
├─────────────────────┤
│  Spring Boot API        │
│     ├─ Controllers      │
│     ├─ Services         │
│     ├─ Repositories    │
│     ├─ Entities          │
│     └─ Configurations │
├─────────────────────┤
│  PostgreSQL 14+          │
└─────────────────────┘
```

- **Controllers** expose REST endpoints.
- **Services** contain business logic and transactional boundaries.
- **Repositories** are Spring Data JPA interfaces for persistence.
- **Entities** map to database tables via JPA annotations.
- **Configurations** handle JWT, CORS, and data source setup.

---

## Development Guidelines

- Use **GitHub Flow**: `feature/*` branches, pull requests, and continuous integration.
- Write unit tests for every new method.
- Keep API versioning in mind; `api/v1/...` is currently the only stable endpoint.
- Document new endpoints in Swagger / OpenAPI.
- Follow the existing coding style: snake_case for database columns, camelCase for Java fields.

---

## Contributing

Pull requests are welcome. For major changes, open an issue first to discuss your ideas.

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/<name>`).
3. Commit your changes with clear, short messages.
4. Push to your fork and open a pull request.

### Code Style

- Java 17 syntax, no deprecated APIs.
- Follow the existing formatting (imports sorted, 4‑space indentation).
- Run `mvn spotless:apply` before submitting.

---

## License

MIT © 2026 Shubhya Yagami

---

## Changelog

**0.1.0** – Initial release (2026‑09‑28)

- Added JWT authentication and role-based access control.
- Implemented CRUD for products, categories, variants, and orders.
- Setup PostgreSQL schema and Docker Compose.
- Added minimal static front‑end demo.
- Configured CI pipeline with GitHub Actions and code coverage.

---
