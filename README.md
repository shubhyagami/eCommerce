# eCommerce

A lightweight full‑stack e‑commerce prototype built with **Java 17 Spring Boot**, vanilla HTML/CSS, and PostgreSQL. It provides JWT‑based authentication, a RESTful product catalog, a session‑aware cart, and persistent order history. The static front‑end is responsive and works on mobile and desktop.

![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)
![Open issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)
![Stargazers](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Getting started

```bash
# 1️⃣ Clone the repo
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce

# 2️⃣ Create a PostgreSQL user & database
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database

# 3️⃣ Load the initial schema
psql -U your_user -d your_database -f src/main/resources/schema.sql

# 4️⃣ Configure the application
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edit the file with your DB credentials and a secret JWT key

# 5️⃣ Build and run
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

Open a browser at `http://localhost:8000` (or your static‑site server) and register a new user to explore the app.

> **Tips**  
> • Keep the JWT secret out of version control; use an environment variable in production.  
> • The API defaults to `http://localhost:8080`.

---

## Features

- **JWT authentication** – secure login/registration with hashed passwords.  
- **RESTful product catalog** – CRUD for products, variants, and SKUs.  
- **Session‑aware cart** – cart data persists across page loads and browser tabs.  
- **Order history** – detailed, per‑user transaction records.  
- **Responsive UI** – plain HTML/CSS that adapts to all screen sizes.

---

## Tech stack

| Layer | Technology |
|-------|------------|
| Backend | Java 17, Spring Boot, Spring Data JPA, Spring Security, JWT |
| Frontend | Plain HTML5 & CSS |
| Database | PostgreSQL |
| Build | Maven |
| CI | GitHub Actions |

---

## Prerequisites

- Java 17 (JDK or JRE)  
- Maven 3.9+  
- PostgreSQL 13+  
- Git  
- A modern web browser (Chrome, Firefox, Edge)

---

## Installation

### Database

```bash
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database
psql -U your_user -d your_database -f src/main/resources/schema.sql
```

### Configuration

Create `src/main/resources/application.properties` from the example and set the required values:

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_user
spring.datasource.password=your_password

# JWT
app.jwt.secret=YOUR_SECURE_JWT_SECRET
```

---

### Build

```bash
mvn clean package
```

The executable jar is placed at `target/ecommerce-0.1.0.jar`.

### Run

```bash
java -jar target/ecommerce-0.1.0.jar
```

The API listens on `http://localhost:8080`.

---

## Running the front‑end

The front‑end is a static site. Open `frontend/index.html` directly or serve it with a simple HTTP server.

```bash
# Python 3 example
python -m http.server 8000
```

The static site can be accessed at `http://localhost:8000` and will communicate with the API at `http://localhost:8080`.

---

## Testing

```bash
mvn test
```

All unit and integration tests run here; the GitHub Actions workflow runs them on every push.

---

## Development guidelines

- Work in short, focused feature branches.  
- Squash commits before submitting a pull request.  
- Run `mvn spotless:apply` to enforce consistent code formatting.  
- Keep `schema.sql` in sync with JPA entity changes.

---

## Contributing

1. Fork the repository.  
2. Create a feature branch: `git checkout -b feature/awesome-feature`.  
3. Commit your changes.  
4. Push the branch: `git push origin feature/awesome-feature`.  
5. Open a pull request with a concise description.

All contributions are welcome. Please run tests locally and ensure CI passes before submitting a PR.

---

## License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

---

## Changelog

| Date | Change |
|------|--------|
| 2026‑09‑01 | Re‑structured README, trimmed content, added concise feature list |
| 2026‑08‑21 | Minor wording updates for clarity |
| 2026‑08‑20 | Updated CI badge and fixed table formatting |

---
