# eCommerce

A lightweight full‑stack e‑commerce prototype built with **Java 17 + Spring Boot 3**.  
The back‑end exposes a REST API for products, categories, variants, and orders, while a vanilla HTML/CSS front‑end consumes that API.  
The stack demonstrates JWT authentication, role‑based access, Spring Security, Spring Data JPA, and PostgreSQL.

![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![Test status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)  
![Coverage](https://img.shields.io/codecov/c/github/shubhyagami/eCommerce?style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Quick start

```bash
$ git clone https://github.com/shubhyagami/eCommerce.git
$ cd eCommerce
$ mvn clean package
$ java -jar target/ecommerce-0.1.0.jar
```

The API is available at `http://localhost:8080`.  
To start the static front‑end:

```bash
cd frontend
python -m http.server 8000
```

Open `http://localhost:8000` in a browser.

---

## Table of contents

- [Getting started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Docker](#docker)
  - [Local setup](#local-setup)
- [Features](#features)
- [Running tests](#running-tests)
- [Development](#development)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Getting started

### Prerequisites

- **Java 17** (JDK or JRE)
- **Maven 3.9+**
- **PostgreSQL 13+**
- **Git**
- (Optional) **Docker**

### Docker

The repository ships with a `docker-compose.yml`.  

```bash
docker compose up -d
```

starts the API on `localhost:8080` and a PostgreSQL instance on `localhost:5432`.

### Local setup

1. **Create a database user and schema**

   ```bash
   sudo -u postgres createuser -P your_user
   sudo -u postgres createdb -O your_user your_database
   ```

2. **Load the schema**

   ```bash
   psql -U your_user -d your_database -f src/main/resources/schema.sql
   ```

3. **Configure the application**

   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```

   Edit `application.properties` and set:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_user
   spring.datasource.password=your_password

   app.jwt.secret=${JWT_SECRET}
   ```

   > **Security** – Never commit a hard‑coded JWT secret. In production set `JWT_SECRET` via an environment variable or a secrets manager.

4. **Run the application**

   ```bash
   mvn clean package
   java -jar target/ecommerce-0.1.0.jar
   ```

---

## Features

| Feature | Description |
|---------|-------------|
| **JWT authentication** | Secure login, salted password hashing, role‑based access |
| **RESTful product API** | CRUD for categories, products, variants, SKUs |
| **Session‑aware cart** | Persisted per user, survives browser restarts |
| **Order history** | User‑specific transactions, exportable to CSV or PDF |
| **Responsive front‑end** | Plain HTML5/CSS, mobile‑first layout |
| **CI/CD** | GitHub Actions – build, test, coverage on every push |

---

## Running tests

```bash
mvn test
```

All unit and integration tests are executed locally and via GitHub Actions.

---

## Development

- Branch naming convention: `feature/...`, `bugfix/...`, `chore/...`.
- Keep commits focused; squash before opening a pull request.
- Run `mvn spotless:apply` to format and lint the code.
- Update `schema.sql` whenever entity mappings change.
- Ensure all tests pass locally; CI must be green before merging.

---

## Contributing

1. Fork the repository.  
2. Create a feature branch: `git checkout -b feature/awesome`.  
3. Commit and push your changes.  
4. Open a pull request with a concise description.  
5. Make sure all tests pass and CI is green.

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
