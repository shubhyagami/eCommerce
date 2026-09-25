[K[2m  [2mmodel deepseek-ai/deepseek-v4.1-flash failed, trying next...[0m[0m
# eCommerce

A lightweight full‑stack e‑commerce prototype written in **Java 17** and **Spring Boot 3**.  
The REST API implements product, category, variant, and order management, and a minimal
HTML/CSS landing page demonstrates how to consume the API.

It follows production‑grade practices: JWT authentication, role‑based access control,
Spring Data JPA, PostgreSQL persistence, and CI/CD via GitHub Actions.

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
- [Local Development (without Docker)](#local-development-without-docker)
- [Features](#features)
- [Architecture](#architecture)
- [Development Guidelines](#development-guidelines)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Prerequisites

- Java 17
- Maven 3.8+
- PostgreSQL 14+
- Docker (for quick start)
- Optional: `python` for the static front‑end demo

---

## Getting Started

```bash
# Clone the repository
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce

# Start the API and PostgreSQL using Docker Compose
docker compose up -d

# The API is now reachable at http://localhost:8080
```

> **Tip** – The static UI can be served with any web server.  
> For a quick demo run `python -m http.server 8000` from the `frontend` directory.

---

## Local Development (without Docker)

1. **Create a PostgreSQL user and database**  

   ```bash
   sudo -u postgres createuser -P your_user
   sudo -u postgres createdb -O your_user your_database
   ```

2. **Run the schema migration**  

   ```bash
   psql -U your_user -d your_database -f src/main/resources/schema.sql
   ```

3. **Configure the application**  

   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```

   Edit the file with your credentials and the JWT secret:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   app.jwt.secret=${JWT_SECRET}
   ```

   Set the environment variable for the secret:

   ```bash
   export JWT_SECRET="your_jwt_secret_key"
   ```

4. **Build and run**  

   ```bash
   mvn clean package
   java -jar target/ecommerce-0.1.0.jar
   ```

---

## Features

- **JWT + RBAC** – Secure authentication, salted password hashing, `ADMIN` & `CUSTOMER` roles.
- **Product CRUD** – Manage categories, products, variants, and SKUs via REST.
- **Persisted Cart** – Authenticated carts survive browser sessions.
- **Order Management** – Create, read, cancel, export orders in CSV or PDF.
- **Responsive UI** – Minimal, mobile‑first static landing page.
- **CI/CD** – GitHub Actions for build, test, and coverage.

---

## Architecture

```
Frontend (HTML/CSS) ←→ Spring Boot REST API ←→ Spring Data JPA + PostgreSQL
```

- **Security** – JWT tokens, role checks in controller layers.
- **Persistence** – Initial DB schema in `schema.sql`; future migrations can use Flyway.
- **Testing** – JUnit + Spring Test; integration tests exercise the REST endpoints.

---

## Development Guidelines

| Topic              | Recommendation |
|--------------------|----------------|
| Branch naming      | `feature/`, `bugfix/`, `chore/` |
| Commits            | Atomic, descriptive (e.g. `feat: add product search`) |
| Code style         | Run `mvn spotless:apply` before committing |
| Schema migrations  | Keep `schema.sql` or Flyway scripts in sync with JPA entities |
| CI                 | All tests must pass and the build must be green before merging |

---

## Contributing

1. Fork the repository.
2. Create a topic branch: `git checkout -b feature/your-feature`.
3. Make changes, run tests, and commit with clear, conventional messages.
4. Push and open a pull request.
5. Ensure all CI checks pass before merging.

---

## License

MIT – see the [LICENSE](LICENSE) file.

---

## Changelog

- **2026‑09‑24** – Minor README cleanup and Docker instructions.  
- **2026‑09‑20** – Updated badges, added concise feature list.  
- **2026‑09‑18** – Added detailed feature table and testing section.  
- **2026‑09‑17** – Refactored sections for clarity.  
- **2026‑09‑01** – Simplified installation guidance.

---
