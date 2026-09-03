# eCommerce

A lightweight, full‑stack e‑commerce solution built with **Java 17 Spring Boot**, plain HTML5/CSS, and PostgreSQL. It offers JWT‑based authentication, a RESTful product catalog, session‑aware carts, and persistent order history—all served through a responsive UI that works on mobile and desktop.

---

![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)
![Open Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?color=important&style=flat-square)
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Table of Contents

- [Getting Started](#getting-started)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
  - [Clone the repo](#clone-the-repo)
  - [Set up PostgreSQL](#set-up-postgresql)
  - [Configure the back‑end](#configure-the-back-end)
  - [Build the project](#build-the-project)
  - [Run the back‑end](#run-the-back-end)
  - [Serve the front‑end](#serve-the-front-end)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [Development Guidelines](#development-guidelines)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Getting Started

```bash
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
```

1. Install PostgreSQL (≥ 13) and create a database user.  
2. Edit `src/main/resources/application.properties` with your connection details and JWT secret.  
3. Build and run the application.  
4. Open the static front‑end in a browser.

---

## Features

- **JWT Authentication** – secure login/registration with hashed passwords.  
- **RESTful Product Catalog** – CRUD for products, variants, and SKUs.  
- **Responsive UI** – plain HTML/CSS that adapts to all screen sizes.  
- **Session‑aware Cart** – cart contents persist across page loads and tabs.  
- **Order History** – detailed transaction logs per user.  

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| **Back‑end** | Java 17, Spring Boot, Spring Data JPA, Spring Security, JWT |
| **Front‑end** | Plain HTML5, CSS |
| **Database** | PostgreSQL |
| **Build** | Maven |
| **CI** | GitHub Actions |

---

## Prerequisites

- Java 17 (JDK or JRE)
- Maven 3.9+
- PostgreSQL 13+ (or newer)
- Git
- Web browser (Chrome/Firefox/Edge)

---

## Installation

### Clone the repo

```bash
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
```

### Set up PostgreSQL

```bash
# Create a user and database
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database

# Load schema
psql -U your_user -d your_database -f src/main/resources/schema.sql
```

### Configure the back‑end

Edit `src/main/resources/application.properties`:

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_user
spring.datasource.password=your_password

# JWT
app.jwt.secret=YOUR_SECURE_JWT_SECRET
```

> **Tip:** Keep the JWT secret out of source control—use an environment variable or a secrets manager in production.

### Build the project

```bash
mvn clean package
```

The jar will be located at `target/ecommerce-0.1.0.jar`.

### Run the back‑end

```bash
java -jar target/ecommerce-0.1.0.jar
```

The API server starts on `http://localhost:8080`.

### Serve the front‑end

The front‑end is a static site. Open `frontend/index.html` directly in a browser, or serve it with a simple HTTP server:

```bash
# Example with Python 3
python -m http.server 8000
```

The front‑end will communicate with the API at `localhost:8080`.

---

## Running the Application

After the back‑end is running, open `http://localhost:8000` (or the URL of your static server). Register a new user, log in, browse products, add items to the cart, and place orders. All data is persisted in PostgreSQL.

---

## Testing

Run the full test suite with:

```bash
mvn test
```

The CI workflow automatically executes these tests on every push.

---

## Development Guidelines

- Keep feature branches focused and short.  
- Squash commits before opening a pull request.  
- Run `mvn spotless:apply` to format code.  
- Refresh `src/main/resources/schema.sql` when schema changes are made.

---

## Contributing

1. Fork the repository.  
2. Create a feature branch (`git checkout -b feature/awesome-feature`).  
3. Commit your changes (`git commit -m "Add awesome feature"`).  
4. Push the branch (`git push origin feature/awesome-feature`).  
5. Open a pull request with a concise description and any relevant test results.

All contributions are welcome. Please run tests locally and ensure CI passes.

---

## License

This project is licensed under the MIT License – see the [`LICENSE`](LICENSE) file for details.

---

## Changelog

| Date | Change |
|------|--------|
| 2026‑09‑01 | Updated README structure and added concise feature list. |
| 2026‑08‑21 | Refined README for readability. |
| 2026‑08‑20 | Verified CI pipeline stability. |
| 2026‑08‑07 | Fixed checkout‑branch bug (PR #42). |
