# eCommerce

A lightweight, full‑stack e‑commerce solution built with **Java 17 Spring Boot**, plain HTML5/CSS, and PostgreSQL. It offers JWT‑based authentication, a RESTful product catalog, session‑aware carts, and persistent order history—all served through a responsive UI that works on mobile and desktop.

![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)
![Open issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)
![Stargazers](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Quick start

```bash
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce

# Create the database
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database
psql -U your_user -d your_database -f src/main/resources/schema.sql

# Configure application
cp src/main/resources/application.properties.example src/main/resources/application.properties
# edit the new file with your DB credentials and JWT secret

# Build and run
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

Open your web browser at `http://localhost:8000` (or the URL of the static server you choose) and register a new user to try the application.

---

## Table of contents

- [Features](#features)
- [Tech stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
  - [Database setup](#database-setup)
  - [Configuration](#configuration)
  - [Building](#building)
  - [Running](#running)
- [Running the front‑end](#running-the-front-end)
- [Testing](#testing)
- [Development guidelines](#development-guidelines)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Features

- **JWT authentication** – secure login/registration with hashed passwords
- **RESTful product catalog** – CRUD for products, variants and SKUs
- **Session‑aware cart** – cart contents persist across page loads and tabs
- **Order history** – detailed transaction logs per user
- **Responsive UI** – plain HTML/CSS that adapts to all screen sizes

---

## Tech stack

| Layer | Technology |
|-------|------------|
| Back‑end | Java 17, Spring Boot, Spring Data JPA, Spring Security, JWT |
| Front‑end | Plain HTML5, CSS |
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

### Database setup

```bash
# Create a PostgreSQL user and database
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database

# Load initial schema
psql -U your_user -d your_database -f src/main/resources/schema.sql
```

### Configuration

Rename the sample properties file and use your own credentials:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

Edit the new file:

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_user
spring.datasource.password=your_password

# JWT
app.jwt.secret=YOUR_SECURE_JWT_SECRET
```

> **Security tip:** Keep the JWT secret out of source control. In production use an environment variable or a secrets manager.

### Building

```bash
mvn clean package
```

The jar is written to `target/ecommerce-0.1.0.jar`.

### Running

```bash
java -jar target/ecommerce-0.1.0.jar
```

The API server listens on `http://localhost:8080`.

---

## Running the front‑end

The front‑end is a static site. You can open `frontend/index.html` directly in a browser, or serve it with a simple HTTP server:

```bash
# Example with Python 3
python -m http.server 8000
```

The static site will communicate with the API at `http://localhost:8080`.

---

## Testing

Run the test suite:

```bash
mvn test
```

All CI jobs execute the same tests on every push.

---

## Development guidelines

- Work in short, focused feature branches
- Squash commits before submitting a pull request
- Use `mvn spotless:apply` for consistent formatting
- Keep `schema.sql` in sync with the JPA entity changes

---

## Contributing

1. Fork the repository  
2. Create a feature branch (`git checkout -b feature/awesome-feature`)  
3. Commit your changes (`git commit -m "Add awesome feature"`)  
4. Push the branch (`git push origin feature/awesome-feature`)  
5. Open a pull request and provide a concise description

All contributions are welcome. Please run tests locally and ensure CI passes before submitting a PR.

---

## License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.

---

## Changelog

| Date | Change |
|------|--------|
| 2026‑09‑01 | Updated README structure and added concise feature list |
| 2026‑08‑21 | Refined README for readability |
| 2026‑08‑20 | Verified CI pipeline stability |
| 2026‑08‑07 | Fixed checkout‑branch bug (PR #42) |
