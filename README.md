# eCommerce

A lightweight, full‑stack e‑commerce prototype written in **Java 17 Spring Boot** with a static front‑end built from plain HTML/CSS.  
It demonstrates JWT‑based authentication, a RESTful product catalog, a session‑aware shopping cart, and a persistent order history against PostgreSQL.

![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Open issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stargazers](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Quick start

```bash
# 1️⃣ Clone the repository
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce

# 2️⃣ Spin up a PostgreSQL user and database
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database

# 3️⃣ Load the initial schema
psql -U your_user -d your_database -f src/main/resources/schema.sql

# 4️⃣ Configure the application
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edit the file below with your DB credentials and a JWT secret

# 5️⃣ Build and run
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

Open `http://localhost:8080` (or serve the static UI with a lightweight server) and register a new user to explore the app.

> **Tip** – Keep the JWT secret out of version control; use an environment variable or a secrets manager in production.

---

## Core features

- **Secure authentication** – OAuth‑style JWT, hashed passwords, and role‑based access control.  
- **RESTful product API** – CRUD for categories, products, variants, and SKUs.  
- **Session‑aware cart** – cart state persisted in a database, available across tabs and re‑logins.  
- **Order history** – per‑user, per‑transaction records that can be exported.  
- **Responsive UI** – basic HTML/CSS that works on mobile and desktop.  
- **CI‑driven** – automated tests and build checks on every push.

---

## Tech stack

| Layer | Technology |
|-------|------------|
| **Backend** | Java 17, Spring Boot, Spring Data JPA, Spring Security, JWT |
| **Frontend** | Plain HTML5 & CSS (no JS frameworks) |
| **Database** | PostgreSQL |
| **Build** | Maven |
| **CI** | GitHub Actions |

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

> **Security** – Never commit a hard‑coded JWT secret. In production, set the `JWT_SECRET` environment variable or use a secrets manager.

---

## Running locally

```bash
# Build
mvn clean package

# Run
java -jar target/ecommerce-0.1.0.jar
```

The API is exposed at `http://localhost:8080`.

---

## Serving the front‑end

The UI is a static site:

```bash
# Example with the Python HTTP server
python -m http.server 8000
```

Open `http://localhost:8000` in your browser. The UI will automatically target the API at `http://localhost:8080`.

---

## Testing

```bash
mvn test
```

All unit and integration tests are also executed by the GitHub Actions workflow on every push.

---

## Development guidelines

- Keep feature branches short and focused (e.g., `feature/add-coupon-system`).  
- Squash commits before submitting a pull request.  
- Run `mvn spotless:apply` to format and lint.  
- Update `schema.sql` whenever entity mappings change.  
- All tests should pass locally before pushing.

---

## Contributing

1. Fork the repository.  
2. Create a feature branch: `git checkout -b feature/awesome-feature`.  
3. Commit your changes.  
4. Push: `git push origin feature/awesome-feature`.  
5. Open a pull request with a concise description.  

Please run tests locally and verify CI passes before submitting a PR.

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
