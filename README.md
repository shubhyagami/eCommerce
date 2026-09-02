# eCommerce

A lightweight, full‑stack e‑commerce solution built with **Java 17 Spring Boot** on the back‑end, plain HTML5/CSS on the front‑end, and a scalable PostgreSQL database. It provides secure JWT‑based authentication, a RESTful product catalog, session‑aware cart handling, and persistent order history—all served through a responsive UI that works on mobile and desktop.

---

![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build)  
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)  
![Open Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce)  
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## 📋 Table of Contents

- [Getting Started](#getting-started)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
  - [Clone the repository](#clone-the-repository)
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

```sh
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
```

Set up PostgreSQL, create a database user, configure `application.properties`, build the project, run the Spring Boot jar, and open the static front‑end. Detailed steps follow below.

---

## Features

| Feature | Description |
|---------|-------------|
| **JWT Authentication** | Secure login/registration with hashed passwords. |
| **RESTful Product Catalog** | CRUD operations for products, variants, and SKUs. |
| **Responsive UI** | Plain HTML5/CSS that adapts to all screen sizes. |
| **Session‑aware Cart** | Cart contents persist across page loads and tabs. |
| **Order History** | Detailed transaction logs per user. |

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Back‑end | Java 17, Spring Boot, Spring Data JPA, Spring Security, JWT |
| Front‑end | Plain HTML5, CSS |
| Database | PostgreSQL |
| Build | Maven |
| CI | GitHub Actions |

---

## Prerequisites

- Java 17 or newer
- Maven 3.9+
- PostgreSQL 13+ (or any modern PostgreSQL version)
- Git
- A modern web browser (Chrome/Firefox/Edge)

---

## Installation

### Clone the repository

```sh
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce
```

### Set up PostgreSQL

```sh
# Create a database user and database
sudo -u postgres createuser -P your_user
sudo -u postgres createdb -O your_user your_database

# Import the schema
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

### Build the project

```sh
mvn clean package
```

### Run the back‑end

```sh
java -jar target/ecommerce-0.1.0.jar
```

The API is now available at `http://localhost:8080`.

### Serve the front‑end

The front‑end is pure static HTML. Open `frontend/index.html` directly in a browser, or serve it with a static server:

```sh
# Example with Python
python -m http.server 8000
```

The front‑end automatically talks to the back‑end on `localhost:8080`.

---

## Running the Application

Once the back‑end is running, open the front‑end URL in a browser. Register, log in, browse products, add items to the cart, and place orders. All data persists in PostgreSQL.

---

## Testing

Run unit and integration tests with:

```sh
mvn test
```

The GitHub Actions workflow automatically runs these tests on every push.

---

## Development Guidelines

- Keep feature branches short and focused.
- Squash commits before creating a pull request.
- Run `mvn spotless:apply` to enforce code style.
- Refresh the database schema (`src/main/resources/schema.sql`) before major changes.

---

## Contributing

1. Fork the repository.  
2. Create a feature branch (`git checkout -b feature/awesome-feature`).  
3. Commit your changes (`git commit -m "Add awesome feature"`).  
4. Push (`git push origin feature/awesome-feature`).  
5. Open a pull request with a concise description and any relevant test results.

All contributions are welcome. Please run the tests locally and ensure the CI passes.

---

## License

Distributed under the MIT License. See the `LICENSE` file for details.

---

## Changelog

| Date | Change |
|------|--------|
| 2026‑09‑01 | Updated README structure and added concise feature list. |
| 2026‑08‑21 | Refined README for readability. |
| 2026‑08‑20 | Verified CI pipeline stability. |
| 2026‑08‑07 | Fixed checkout‑branch bug (PR #42). |

---
