# eCommerce

A lightweight full‑stack e‑commerce prototype built with **Java 17 Spring Boot**, vanilla HTML/CSS, and PostgreSQL. It offers JWT‑based authentication, a RESTful product catalog, a session‑aware cart, and a persistent order history. The static front‑end is responsive and works on mobile and desktop.

![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Open issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stargazers](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Quick start

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
# Edit the file with your DB credentials and a JWT secret

# 5️⃣ Build and run
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

Open `http://localhost:8000` (or serve `frontend/index.html` with any static server) and register a new user to explore the app.

> **Tip**  
> Keep the JWT secret out of version control; in production use an environment variable.

---

## Features

- **JWT authentication** – secure login/registration with hashed passwords.  
- **RESTful product catalog** – CRUD for products, variants, and SKUs.  
- **Session‑aware cart** – cart data persists across page loads and browser tabs.  
- **Order history** – detailed, per‑user transaction records.  
- **Responsive UI** – plain HTML/CSS that adapts to all screen sizes.

---

## Tech stack

| Layer      | Technology |
|------------|-------------|
| Backend    | Java 17, Spring Boot, Spring Data JPA, Spring Security, JWT |
| Frontend   | Plain HTML5 & CSS |
| Database   | PostgreSQL |
| Build      | Maven |
| CI         | GitHub Actions |

---

## Prerequisites

- Java 17 (JDK or JRE)  
- Maven 3.9+  
- PostgreSQL 13+  
- Git  
- A modern web browser (Chrome, Firefox, Edge)

---

## Configuration

Create `src/main/resources/application.properties` from the example:

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_user
spring.datasource.password=your_password

# JWT
app.jwt.secret=YOUR_SECURE_JWT_SECRET
```

> **Security note** – Never commit the JWT secret. Use an environment variable or a secrets manager in production.

---

## Running the app

```bash
# Build
mvn clean package

# Run
java -jar target/ecommerce-0.1.0.jar
```

The API will be available at `http://localhost:8080`.

---

## Serving the front‑end

The front‑end is a static site.

```bash
# Example with Python 3
python -m http.server 8000
```

Navigate to `http://localhost:8000`.  
The UI will automatically contact the API at `http://localhost:8080`.

---

## Testing

Run the test suite locally:

```bash
mvn test
```

All unit and integration tests are executed by the GitHub Actions workflow on every push.

---

## Development guidelines

- Branches should be short and focused (e.g. `feature/add-coupon-system`).  
- Squash commits before submitting a pull request.  
- Lint and format with `mvn spotless:apply`.  
- Keep `schema.sql` in sync with JPA entity changes.

---

## Contributing

1. Fork the repository.  
2. Create a feature branch: `git checkout -b feature/awesome-feature`.  
3. Commit your changes.  
4. Push the branch: `git push origin feature/awesome-feature`.  
5. Open a pull request with a concise description.

Please run tests locally and ensure CI passes before submitting a PR.

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
