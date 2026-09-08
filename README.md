# eCommerce

A minimal full‑stack e‑commerce prototype built with **Java 17 + Spring Boot 3**.  
The back‑end offers a REST API for products, categories, variants and orders; the front‑end is a vanilla HTML5/CSS page that consumes that API.  
It showcases JWT authentication, role‑based access control, Spring Security, Spring Data JPA and PostgreSQL.

![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![Test status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)  
![Coverage](https://img.shields.io/codecov/c/github/shubhyagami/eCommerce?style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Getting started

These steps will get the application running locally.

```bash
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
```

### Prerequisites

- Java 17 (JDK or JRE)
- Maven 3.9+
- PostgreSQL 13+
- Git
- (Optional) Docker

> **Tip** – The project ships with a `docker-compose.yml`. If you prefer Docker, simply run `docker compose up -d` to spin up the API and a PostgreSQL instance.

### 1. Create a PostgreSQL database

```bash
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database
```

> Or set the `POSTGRES_*` environment variables in the Docker compose file.

### 2. Load the schema

```bash
psql -U your_user -d your_database -f src/main/resources/schema.sql
```

### 3. Configure

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edit the file and set the database credentials and a JWT secret.

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_user
spring.datasource.password=your_password

# JWT
app.jwt.secret=${JWT_SECRET}
```

> **Security note** – Never commit a hard‑coded JWT secret. In production set the `JWT_SECRET` environment variable or use a secrets manager.

### 4. Build and run

```bash
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

The API is now reachable at <http://localhost:8080>.  
Open the static front‑end with any HTTP server, e.g.:

```bash
python -m http.server 8000
```

and visit <http://localhost:8000>.

---

## Features

- **JWT authentication** – secure login, salted password hashing, role‑based access control  
- **Product API** – CRUD for categories, products, variants and SKUs  
- **Session‑aware cart** – persisted in the database and survives browser restarts  
- **Order history** – per‑user transactions, exportable to CSV or PDF  
- **Static front‑end** – responsive, mobile‑first design with plain HTML5/CSS  
- **CI/CD** – GitHub Actions build, unit and integration tests on every push  

---

## Running tests

```bash
mvn test
```

All unit and integration tests run automatically via GitHub Actions.

---

## Development guidelines

- Use descriptive feature‑branch names (e.g. `feature/add-coupon-system`).  
- Keep branches short and squash commits before opening a PR.  
- Run `mvn spotless:apply` to format and lint the code.  
- Update `src/main/resources/schema.sql` whenever JPA entity mappings change.  
- Verify all tests pass locally; the CI should be green before merging.

---

## Contributing

1. Fork the repository.  
2. Create an isolated feature branch: `git checkout -b feature/awesome`.  
3. Commit your changes and push.  
4. Open a pull request with a concise description.  
5. Ensure all tests pass locally and CI is green.

---

## License

MIT – see the [LICENSE](LICENSE) file for details.

---

## Changelog

| Date | Change |
|------|--------|
| 2026‑09‑01 | Refactored README, streamlined sections |
| 2026‑08‑21 | Minor wording tweaks |
| 2026‑08‑20 | Updated CI badge, fixed table formatting |

---
