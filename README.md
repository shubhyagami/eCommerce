# eCommerce

A lightweight, full‑stack e‑commerce prototype written with **Java 17** and **Spring Boot 3**.  
The backend exposes a REST API for products, categories, variants, and orders, while the frontend is a clean, vanilla HTML/CSS landing page that shows how to consume the API.  
The project demonstrates production‑grade practices such as JWT authentication, role‑based access control, Spring Data JPA, and PostgreSQL persistence.

![Build](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![Test](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)  
![Coverage](https://img.shields.io/codecov/c/github/shubhyagami/eCommerce?style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Table of Contents

- [Quick Setup](#quick-setup)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Docker](#docker)
  - [Local Development](#local-development)
- [Features](#features)
- [Architecture](#architecture)
- [Testing](#testing)
- [Development Guidelines](#development-guidelines)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Quick Setup

```bash
# Clone the repo
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce

# Build the backend
mvn clean package

# Run the backend
java -jar target/ecommerce-0.1.0.jar
```

The API is now listening on `http://localhost:8080`.  
Serve the demo UI locally:

```bash
cd frontend
python -m http.server 8000
```

Open `http://localhost:8000` in a browser.

---

## Getting Started

### Prerequisites

- Java 17 (JDK)
- Maven 3.9+ (build)
- PostgreSQL 13+ (or any JDBC‑compatible DB)
- Docker (optional)
- Git

### Docker

The `docker‑compose.yml` file starts the API and PostgreSQL:

```bash
docker compose up -d
```

Endpoints:

| Service | URL |
|---------|-----|
| API     | `http://localhost:8080` |
| DB      | `localhost:5432` |

### Local Development

1. **Create database user & database**

   ```bash
   sudo -u postgres createuser -P your_user
   sudo -u postgres createdb -O your_user your_database
   ```

2. **Run schema migration**

   ```bash
   psql -U your_user -d your_database -f src/main/resources/schema.sql
   ```

3. **Configure**

   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```
   Edit the file with your credentials:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   app.jwt.secret=${JWT_SECRET}
   ```

   **Tip:** Keep `JWT_SECRET` as an environment variable.

4. **Build & run**

   ```bash
   mvn clean package
   java -jar target/ecommerce-0.1.0.jar
   ```

---

## Features

| Feature | Description |
|---------|-------------|
| **JWT / RBAC** | Secure authentication with salted password hashing and role‑based access. |
| **Product Management** | CRUD for categories, products, variants, and SKUs via REST. |
| **Persistent Cart** | Authenticated users have a cart that survives browser sessions. |
| **Order Functions** | Create, read, cancel, and export orders as CSV or PDF. |
| **Responsive Frontend** | Minimal, mobile‑first UI built with plain HTML/CSS. |
| **CI/CD** | GitHub Actions for build, test, and coverage reporting. |

---

## Architecture

- **Backend** – Spring Boot 3, Spring Data JPA, PostgreSQL, JWT authentication.
- **Frontend** – Static assets served with a simple HTTP server (no SPA framework).
- **Database** – `schema.sql` defines the schema; migrations can be extended with Flyway.
- **Security** – Roles (`ADMIN`, `CUSTOMER`) protect sensitive endpoints.

---

## Testing

Run all unit & integration tests locally:

```bash
mvn test
```

All tests execute automatically on every push via GitHub Actions.

---

## Development Guidelines

| Topic | Recommendation |
|-------|----------------|
| **Branch naming** | Use prefixes: `feature/`, `bugfix/`, `chore/`. |
| **Commit style** | Keep changes atomic; squash before PR. |
| **Code style** | Run `mvn spotless:apply` before committing. |
| **Schema changes** | Update `schema.sql` for any entity mapping changes. |
| **CI** | All tests must pass and the build must be green before merging. |

---

## Contributing

1. Fork the repository.  
2. Create a feature branch: `git checkout -b feature/your-feature`.  
3. Commit changes, push, and open a pull request with a clear title and description.  
4. Ensure all CI checks succeed before merging.

---

## License

MIT – see the [LICENSE](LICENSE) file.

---

## Changelog

| Date | Change |
|------|--------|
| 2026‑09‑20 | Minor README cleanup and updated Docker instructions. |
| 2026‑09‑18 | Added detailed feature table and testing section. |
| 2026‑09‑17 | Refactored sections for clarity. |
| 2026‑09‑01 | Simplified installation guidance. |
| 2026‑08‑21 | Minor wording corrections. |
| 2026‑08‑20 | Updated CI badges and fixed table formatting. |
