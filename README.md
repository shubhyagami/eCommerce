# eCommerce

A lightweight, full‑stack e‑commerce prototype written with **Java 17** and **Spring Boot 3**.  
The backend exposes a robust REST API for products, categories, variants, and orders, while the frontend is a clean, vanilla HTML/CSS landing page that demonstrates how the API is consumed.  
The project showcases industry‑grade practices such as JWT authentication, role‑based access control, Spring Data JPA, and PostgreSQL persistence.

![Build](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![Test](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)  
![Coverage](https://img.shields.io/codecov/c/github/shubhyagami/eCommerce?style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Table of Contents

- [Quick Start](#quick-start)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Docker Setup](#docker-setup)
  - [Local Setup](#local-setup)
- [Features](#features)
- [Architecture](#architecture)
- [Testing](#testing)
- [Development Guidelines](#development-guidelines)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Quick Start

```bash
# Clone the repository
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce

# Build and run the backend
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

The API is now available at `http://localhost:8080`.  
The static frontend can be served locally with a simple HTTP server:

```bash
cd frontend
python -m http.server 8000
```

Open `http://localhost:8000` in your browser to see the demo UI.

---

## Getting Started

### Prerequisites

- Java 17 (JDK)
- Maven 3.9+ (for building)
- PostgreSQL 13+ (or any JDBC‑compatible database)
- Docker (optional, for quick composition)
- Git

### Docker Setup

The provided `docker-compose.yml` orchestrates the API and PostgreSQL:

```bash
docker compose up -d
```

Endpoints after the stack starts:

| Service | URL |
|---------|-----|
| API     | `http://localhost:8080` |
| DB      | `localhost:5432` |

### Local Setup

1. **Create database user and database**

   ```bash
   sudo -u postgres createuser -P your_user
   sudo -u postgres createdb -O your_user your_database
   ```

2. **Run the schema migration**

   ```bash
   psql -U your_user -d your_database -f src/main/resources/schema.sql
   ```

3. **Copy example configuration**

   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```

   Edit `application.properties` with your credentials and JWT secret:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   app.jwt.secret=${JWT_SECRET}
   ```

   **Tip:** Keep `JWT_SECRET` as an environment variable in production.

4. **Build and launch**

   ```bash
   mvn clean package
   java -jar target/ecommerce-0.1.0.jar
   ```

---

## Features

| Feature | Description |
|---------|-------------|
| **JWT / RBAC** | Secure authentication with salted password hashing and role‑based access control. |
| **Product API** | CRUD for categories, products, variants, and SKUs, all exposed via REST. |
| **Persistent Cart** | Authenticated users have a cart that survives browser sessions. |
| **Order Operations** | Create, read, cancel, and export orders in CSV or PDF. |
| **Responsive UI** | Lightweight, mobile‑first front‑end with pure HTML/CSS. |
| **CI/CD** | GitHub Actions for building, testing, and reporting coverage. |

---

## Architecture

- **Backend**: Spring Boot 3, Spring Data JPA, PostgreSQL, JWT authentication.  
- **Frontend**: Static assets served via a simple HTTP server (no SPA framework).  
- **Database Layer**: SQL schema in `schema.sql`; migrations handled manually or via Flyway if extended.  
- **Security**: Roles (`ADMIN`, `CUSTOMER`) enforce access to sensitive endpoints.  

---

## Testing

Run all unit & integration tests locally:

```bash
mvn test
```

Tests are automatically executed on every push by GitHub Actions. The test results are reported in the pipeline.

---

## Development Guidelines

| Topic | Recommendation |
|-------|----------------|
| **Branching** | Prefix branches with `feature/`, `bugfix/`, or `chore/`. |
| **Commits** | Keep changes atomic; squash before PR. |
| **Style** | Run `mvn spotless:apply` before committing. |
| **DB Changes** | Update `schema.sql` whenever entity mappings change. |
| **CI** | All tests must pass and the build must be green before merging. |

---

## Contributing

1. Fork the repo.  
2. Create a feature branch: `git checkout -b feature/your-feature`.  
3. Commit changes and push: `git push -u origin feature/your-feature`.  
4. Open a pull request with a clear title and description.  
5. Ensure all CI checks succeed before merging.

---

## License

MIT License – see the [LICENSE](LICENSE) file.

---

## Changelog

| Date | Change |
|------|--------|
| 2026‑09‑18 | Minor README cleanup and added Docker instructions. |
| 2026‑09‑17 | Polished README for clarity and improved developer onboarding. |
| 2026‑09‑01 | Refactored README structure and streamlined sections. |
| 2026‑08‑21 | Minor wording and documentation tweaks. |
| 2026‑08‑20 | Updated CI badges and fixed table formatting. |
