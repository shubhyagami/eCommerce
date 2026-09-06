# eCommerce

A lightweight, full‑stack e‑commerce prototype built with **Java 17 + Spring Boot**.  
The back‑end exposes a RESTful API for products and orders, and the front‑end is a plain HTML5/CSS static site that consumes that API.  
Key technologies: JWT authentication, Spring Security, Spring Data JPA and PostgreSQL.

![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Open issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stargazers](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Quick Start

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

### 3. Configure the application

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edit the file, setting your DB credentials and a JWT secret.
```

> **Security tip** – Never commit a hard‑coded JWT secret. In production set the `JWT_SECRET` environment variable or use a secrets manager.

### 4. Build and run

```bash
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

Open <http://localhost:8080> to register a new user and start browsing the site.

---

## Core Features

| Feature | Description |
|---|---|
| **JWT Authentication** | Secure login, role‑based access control, salted password hashing |
| **Product API** | CRUD endpoints for categories, products, variants, and SKUs |
| **Session‑aware Cart** | Persisted in the database, survives browser restarts |
| **Order History** | Per‑user transactions, exportable to CSV or PDF |
| **Static Front‑End** | Vanilla HTML5/CSS, responsive mobile‑first design |
| **CI/CD** | GitHub Actions builds, unit and integration tests on every push |

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

---

## Running Locally

```bash
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

The REST API is available at <http://localhost:8080>.

---

## Serving the Front‑End

The UI is a static site. A quick way to serve it:

```bash
python -m http.server 8000
```

Open <http://localhost:8000> – it automatically points to the API at <http://localhost:8080>.

---

## Testing

```bash
mvn test
```

All unit and integration tests run on every push via the GitHub Actions workflow.

---

## Development Guidelines

- Use short, descriptive feature branches (e.g., `feature/add-coupon-system`).
- Squash commits before opening a PR.
- Run `mvn spotless:apply` to format and lint the code.
- Update `schema.sql` when JPA entity mappings change.
- Verify all tests pass locally and CI is green before merging.

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
