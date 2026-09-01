# eCommerce

A lightweight, full‑stack e‑commerce solution built with Java **Spring Boot** on the back‑end, standard HTML5 on the front‑end, and a scalable PostgreSQL database. It offers secure JWT‑based authentication, a RESTful product catalog, session‑aware cart handling, and persistent order history, all delivered through a responsive UI that works on mobile and desktop.

---

[![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build)](https://github.com/shubhyagami/eCommerce/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/shubhyagami/eCommerce/blob/main/LICENSE)
[![Open Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce)](https://github.com/shubhyagami/eCommerce/issues)
[![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)](https://github.com/shubhyagami/eCommerce)

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
  - [Back‑end](#back-end)
  - [Front‑end](#front-end)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Development](#development)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Features

- **Secure Authentication** – JWT login/registration with hashed passwords.  
- **RESTful Product Catalog** – CRUD for products, variants, and SKUs.  
- **Responsive UI** – HTML5 templates that adapt to any screen size.  
- **Session‑aware Cart** – Items persist across page loads and browser tabs.  
- **Order History** – Detailed logs of all transactions per user.

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Back‑end | Java 17, Spring Boot, Spring Data JPA, Spring Security, JWT |
| Front‑end | Plain HTML5, CSS (no framework), optional Live Server |
| Database | PostgreSQL |
| Build | Maven |
| CI | GitHub Actions (unit & integration tests) |

---

## Prerequisites

- Java 17 or newer
- Maven 3.9+
- PostgreSQL 13+ (or any recent version)
- Git
- A web browser (Chrome/Firefox/Edge)

---

## Installation

### 1. Clone the repository

```bash
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
```

### 2. Set up the database

```bash
# Create a database user and database
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database

# Run the schema script
psql -U your_user -d your_database -f src/main/resources/schema.sql
```

### 3. Configure the back‑end

Edit `src/main/resources/application.properties`:

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_user
spring.datasource.password=your_password

# JWT
app.jwt.secret=YOUR_SECURE_JWT_SECRET
```

### 4. Build the back‑end

```bash
mvn clean package
```

### 5. Run the back‑end

```bash
java -jar target/ecommerce-0.1.0.jar
```

The API will be available at `http://localhost:8080`.

### 6. Serve the front‑end

The front‑end is pure static HTML. You can:

- Open `frontend/index.html` directly in a browser, or
- Use a static server such as **Live Server** (`extension: Live Server` in VS Code) or `python -m http.server`.

The front‑end will automatically talk to the back‑end on `localhost:8080`.

---

## Running the Application

Once the back‑end is running, navigate to the front‑end URL.  
You can register a new account, login, browse products, add items to the cart, and place orders. All data is persisted in PostgreSQL.

---

## Testing

Run unit and integration tests with:

```bash
mvn test
```

The GitHub Actions workflow automatically runs these tests on every push.

---

## Development

- Keep your feature branches short and focus on a single change.
- Squash commits before creating a pull request.
- Ensure code style and formatting match the existing style; run `mvn spotless:apply` if needed.
- Refresh the database schema before major changes (`src/main/resources/schema.sql`).

---

## Contributing

1. Fork the repo.
2. Create a feature branch (`git checkout -b feature/awesome-feature`).
3. Commit your changes (`git commit -m "Add awesome feature"`).
4. Push (`git push origin feature/awesome-feature`).
5. Open a pull request with a concise description of the changes and any relevant test results.

All contributions are welcomed. Please run tests locally and make sure the CI passes.

---

## License

Distributed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Changelog

| Date | Change |
|------|--------|
| 2026‑09‑01 | Updated README structure and added concise feature list. |
| 2026‑08‑21 | Refined README for readability. |
| 2026‑08‑20 | Verified CI pipeline stability. |
| 2026‑08‑07 | Fixed checkout‑branch bug (PR #42). |

---
