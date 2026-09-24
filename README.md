[K[2m  [2mmodel deepseek-ai/deepseek-v4.1-flash failed, trying next...[0m[0m
# eCommerce

A lightweight full‑stack e‑commerce prototype built with **Java 17** and **Spring Boot 3**.  
The REST API powers product, category, variant, and order management, while a plain HTML/CSS landing page demonstrates how to consume the API.  
The project showcases production‑grade patterns: JWT authentication, role‑based access, Spring Data JPA, PostgreSQL persistence, and CI/CD via GitHub Actions.

![Build](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![Test](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)  
![Coverage](https://img.shields.io/codecov/c/github/shubhyagami/eCommerce?style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Quick Start

```bash
# Clone the repo
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce

# Bring up the API and database with Docker
docker compose up -d

# The API is now available at http://localhost:8080
```

> **Tip:** The static UI can be served with any web server.  
> For a quick demo run `python -m http.server 8000` from the `frontend` directory.

---

## Local Development (without Docker)

```bash
# 1. Create PostgreSQL user and database
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database

# 2. Run schema migration
psql -U your_user -d your_database -f src/main/resources/schema.sql

# 3. Configure the application
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edit with your credentials
# spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
# spring.datasource.username=your_user
# spring.datasource.password=your_password
# app.jwt.secret=${JWT_SECRET}
# (set JWT_SECRET as an env variable)

# 4. Build and run
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

---

## Features

| Feature | Description |
|---------|-------------|
| **JWT + RBAC** | Secure authentication, salted password hashing, `ADMIN` & `CUSTOMER` roles |
| **Product CRUD** | Manage categories, products, variants, and SKUs via REST |
| **Persisted Cart** | Authenticated carts survive browser sessions |
| **Order Management** | Create, read, cancel, CSV/PDF export |
| **Responsive UI** | Minimal mobile‑first static page |
| **CI/CD** | GitHub Actions: build, test, coverage |


---

## Architecture

```
┌─────────────────────┐
│  Frontend (HTML/CSS) │
└───────────────┬──────┘
                │
        Spring Boot REST API
                │
  ┌───────────────────────────────┐
  │  Spring Data JPA + PostgreSQL │
  └───────────────────────────────┘
```

- **Security** – JWT tokens, role checks in controller layers.  
- **Persistence** – `schema.sql` contains the initial DB structure; future migrations can use Flyway.  
- **Testing** – JUnit + Spring Test; integration tests exercise the REST endpoints.

---

## Development Guidelines

| Topic | Recommendation |
|-------|----------------|
| **Branch naming** | `feature/`, `bugfix/`, `chore/` |
| **Commits** | Atomic, descriptive (e.g. `feat: add product search`) |
| **Code style** | Run `mvn spotless:apply` before committing |
| **Schema** | Keep `schema.sql` in sync with JPA entities |
| **CI** | All tests must pass and build must be green before merging |

---

## Contributing

1. Fork the repository.  
2. Create a topic branch: `git checkout -b feature/your-feature`.  
3. Make changes, run tests, commit with clear messages.  
4. Push and open a pull request.  
5. Ensure all CI checks succeed before merging.

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
- **2026‑08‑21** – Minor wording corrections.  
- **2026‑08‑20** – Updated CI badges and fixed table formatting.
