# eCommerce

A lightweight full‑stack e‑commerce prototype built with **Java 17 + Spring Boot**.  
The back‑end exposes a REST API for products and orders, while the front‑end is a plain HTML5/CSS static site that consumes that API.  
Key technologies: JWT authentication, Spring Security, Spring Data JPA, PostgreSQL.

![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![Test status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Open issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stargazers](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Quick start

```bash
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
```

### 1. Create a PostgreSQL database

```bash
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database
```

### 2. Load the schema

```bash
psql -U your_user -d your_database -f src/main/resources/schema.sql
```

### 3. Configure

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edit the file: set DB credentials and a JWT secret.
```

> **Security note** – Never commit a hard‑coded JWT secret. In production set the `JWT_SECRET` environment variable or use a secrets manager.

### 4. Build and run

```bash
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

Open <http://localhost:8080> to register a new user and start browsing.

---

## Core features

| Feature | Description |
|---|---|
| **JWT Authentication** | Secure login, role‑based access control, salted password hashing |
| **Product API** | CRUD endpoints for categories, products, variants, and SKUs |
| **Session‑aware Cart** | Persisted in the database, survives browser restarts |
| **Order History** | Per‑user transactions, exportable to CSV or PDF |
| **Static Front‑End** | Vanilla HTML5/CSS, responsive mobile‑first design |
| **CI/CD** | GitHub Actions builds, unit & integration tests on every push |

---

## Prerequisites

- Java 17 (JDK or JRE)
- Maven 3.9+
- PostgreSQL 13+
- Git
- Modern web browser (Chrome, Firefox, Edge)

---

## Configuration

Create `src/main/resources/application.properties` from the example and fill in your details:

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_user
spring.datasource.password=your_password

# JWT
app.jwt.secret=${JWT_SECRET}
```

If you prefer environment variables, you can set them directly; the application will pick them up.

---

## Running locally

```bash
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

The REST API is reachable at <http://localhost:8080>.  
The static front‑end can be served with any HTTP server; for a quick test:

```bash
python -m http.server 8000
```

and open <http://localhost:8000>.

---

## Testing

```bash
mvn test
```

All unit and integration tests run on every push via the GitHub Actions workflow.

---

## Development guidelines

- Keep feature branches short and descriptive (e.g. `feature/add-coupon-system`).
- Squash commits before opening a PR.
- Run `mvn spotless:apply` to format and lint the code.
- Update `schema.sql` whenever JPA entity mappings change.
- Verify tests pass locally and CI passes before merging.

---

## Contributing

1. Fork the repository.  
2. Create an isolated feature branch: `git checkout -b feature/awesome`.  
3. Commit your changes and push.  
4. Open a pull request with a concise description.  
5. Make sure all tests pass locally and CI is green.

---

## License

MIT – see the [LICENSE](LICENSE) file for details.

---

## Changelog

| Date | Change |
|---|---|
| 2026‑09‑01 | Refactored README, streamlined sections |
| 2026‑08‑21 | Minor wording tweaks |
| 2026‑08‑20 | Updated CI badge, fixed table formatting |

---
