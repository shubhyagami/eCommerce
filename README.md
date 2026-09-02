# eCommerce

A lightweight, full‑stack e‑commerce application built with **Spring Boot** (Java 17) on the back‑end, plain HTML5/CSS on the front‑end, and PostgreSQL for persistence. It offers JWT‑based authentication, a RESTful product catalog, session‑aware cart handling, and persistent order history—all served through a responsive UI that works on desktop and mobile.

[![Build](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build)](https://github.com/shubhyagami/eCommerce/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce)](https://github.com/shubhyagami/eCommerce/issues)
[![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)](https://github.com/shubhyagami/eCommerce)

---

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Backend](#backend)
  - [Frontend](#frontend)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Development](#development)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Overview

The project exposes a REST API for products and orders while serving a static front‑end that consumes these endpoints. User authentication is handled via JWT, and passwords are stored hashed with BCrypt. The catalog supports products, variants, and SKUs. Cart contents are stored in the session and persisted on checkout. All data is stored in PostgreSQL.

---

## Features

- **Secure JWT authentication** – register, login, refresh tokens; passwords hashed with BCrypt.
- **RESTful API** – CRUD endpoints for products, variants, SKUs, cart, and orders.
- **Session‑aware cart** – items survive page reloads and multiple tabs.
- **Order history** – users can view past orders with full details.
- **Responsive HTML5 UI** – adapts to all screen sizes without a JavaScript framework.
- **Unit & integration tests** – run automatically via GitHub Actions.

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Back‑end | Java 17, Spring Boot 3, Spring Data JPA, Spring Security, JWT |
| Front‑end | Plain HTML5, CSS |
| Database | PostgreSQL |
| Build | Maven |
| CI | GitHub Actions (unit & integration tests) |

---

## Prerequisites

- Java 17 or newer
- Maven 3.9+
- PostgreSQL 13+ (or a recent compatible version)
- Git
- Web browser (Chrome/Firefox/Edge)

---

## Getting Started

### Backend

```bash
# 1️⃣ Clone the repo
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce

# 2️⃣ Create database and user
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database

# 3️⃣ Load schema
psql -U your_user -d your_database -f src/main/resources/schema.sql

# 4️⃣ Configure application
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edit the file to point to your DB and set a JWT secret
```

```text
# src/main/resources/application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_user
spring.datasource.password=your_password
app.jwt.secret=YOUR_SECURE_JWT_SECRET
```

```bash
# 5️⃣ Build
mvn clean package

# 6️⃣ Run
java -jar target/ecommerce-0.1.0.jar
```

The API will be available at `http://localhost:8080`.

### Frontend

The front‑end is a static site. Open it directly in a browser or serve it with a lightweight server:

```bash
# Option 1: Open file
open frontend/index.html   # macOS
xdg-open frontend/index.html   # Linux

# Option 2: Serve with Python (any port)
python -m http.server 8000 --directory frontend
```

The page will automatically call the back‑end on `localhost:8080`.

---

## Running the Application

1. Start the back‑end (`java -jar target/ecommerce-0.1.0.jar`).
2. Open the front‑end in a browser as described above.
3. Register, log in, browse products, add to cart, and place orders. All data is persisted in PostgreSQL.

---

## Testing

```bash
mvn test
```

The GitHub Actions workflow runs these tests on every push.

---

## Development

- Keep feature branches focused; squash commits before merging.
- Run `mvn spotless:apply` to enforce code style.
- Refresh the database schema (`src/main/resources/schema.sql`) before major changes.
- Verify the back‑end works locally (`curl http://localhost:8080/api/products`) before pushing.

---

## Contributing

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature/awesome-feature`.
3. Commit: `git commit -m "Add awesome feature"`.
4. Push: `git push origin feature/awesome-feature`.
5. Open a pull request with a clear description and test results.

All contributions are welcome. Please run the tests locally and ensure the CI passes.

---

## License

MIT – see the [LICENSE](LICENSE) file.

---

## Changelog

| Date | Change |
|------|--------|
| 2026‑09‑01 | Refactored README, added concise feature list |
| 2026‑08‑21 | Improved readability, updated badges |
| 2026‑08‑20 | Stabilized CI pipeline |
| 2026‑08‑07 | Fixed checkout‑branch bug (PR #42) |
