# eCommerce

A lightweight, full‑stack e‑commerce prototype built with **Java 17 + Spring Boot 3**.  
The back‑end exposes a REST API for products, categories, variants and orders.  
A vanilla HTML/CSS front‑end consumes that API.  
The stack demonstrates JWT authentication, role‑based access, Spring Security, Spring Data JPA, and PostgreSQL.

![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![Test status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)  
![Code coverage](https://img.shields.io/codecov/c/github/shubhyagami/eCommerce?style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Table of contents

- [Getting started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Clone & build](#clone--build)
  - [Running with Docker](#running-with-docker)
  - [Running locally](#running-locally)
- [Features](#features)
- [Running tests](#running-tests)
- [Development guidelines](#development-guidelines)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Getting started

These instructions will get the API and a simple front‑end running on your machine.

### Prerequisites

```
Java 17 (JDK or JRE)
Maven 3.9+
PostgreSQL 13+
Git
(Optional) Docker
```

> **Tip** – The repository ships with a `docker‑compose.yml`.  
> If you prefer Docker, run:

```bash
docker compose up -d
```

> This starts the API on `localhost:8080` and a PostgreSQL instance on `localhost:5432`.

### Clone & build

```bash
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
```

### Configure the database

1. **Create a database user**:

   ```bash
   sudo -u postgres createuser -P your_user
   sudo -u postgres createdb -O your_user your_database
   ```

2. **Load the schema**:

   ```bash
   psql -U your_user -d your_database -f src/main/resources/schema.sql
   ```

3. **Create application properties**:

   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```

   Edit the file and set:

   ```properties
   # PostgreSQL
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_user
   spring.datasource.password=your_password

   # JWT
   app.jwt.secret=${JWT_SECRET}
   ```

   > **Security note** – Never commit a hard‑coded JWT secret. In production set the `JWT_SECRET` environment variable or use a secrets manager.

### Run

```bash
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

The API is now reachable at <http://localhost:8080>.

To view the static front‑end, launch any HTTP server in the `frontend/` directory, e.g.:

```bash
python -m http.server 8000
```

Then open <http://localhost:8000> in a browser.

---

## Features

* **JWT authentication** – salted password hashing, role‑based access
* **RESTful product API** – CRUD for categories, products, variants, SKUs
* **Session‑aware cart** – persisted per user, survives browser restarts
* **Order history** – per‑user transactions, exportable to CSV or PDF
* **Simple front‑end** – responsive, mobile‑first design using plain HTML5/CSS
* **CI/CD** – GitHub Actions build, test, coverage checks on every push

---

## Running tests

```bash
mvn test
```

All unit and integration tests run automatically via GitHub Actions.

---

## Development guidelines

* Branch names: `feature/<short-name>`, `bugfix/<short-name>`, `chore/<short-name>`
* Keep commits focused; squash before opening a PR
* Run `mvn spotless:apply` to format and lint the code
* Update `src/main/resources/schema.sql` when entity mappings change
* Verify tests pass locally; CI must be green before merging

---

## Contributing

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature/awesome`.
3. Commit and push your changes.
4. Open a pull request with a concise description.
5. Ensure all tests pass locally and CI is green.

---

## License

MIT – see the [LICENSE](LICENSE) file.

---

## Changelog

| Date | Change |
|------|--------|
| 2026‑09‑01 | Refactored README, streamlined sections |
| 2026‑08‑21 | Minor wording tweaks |
| 2026‑08‑20 | Updated CI badge, fixed table formatting |
