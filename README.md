[K[2m  [2mmodel z-ai/glm-5.3-flash failed, trying next...[0m[0m
# eCommerce

A lightweight full-stack e-commerce prototype built with **Java 17** and **Spring Boot 3**. The REST API handles product, category, variant, and order management, while a minimal HTML/CSS landing page demonstrates how to consume the API.

It follows production-grade practices: JWT authentication, role-based access control, Spring Data JPA, PostgreSQL persistence, and CI/CD via GitHub Actions.

![Build](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)
![Test](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)
![Coverage](https://img.shields.io/codecov/c/github/shubhyagami/eCommerce?style=flat-square)
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)
![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Local Development (without Docker)](#local-development-without-docker)
- [Features](#features)
- [Architecture](#architecture)
- [Development Guidelines](#development-guidelines)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Prerequisites

- Java 17
- Maven 3.8+
- PostgreSQL 14+
- Docker (for the quick start)
- Optional: Python for the static front-end demo

---

## Getting Started

Clone the repository and start the API with PostgreSQL using Docker Compose:

    git clone https://github.com/shubhyagami/eCommerce.git
    cd eCommerce
    docker compose up -d

The API is available at http://localhost:8080.

> **Tip:** The static UI can be served with any web server. For a quick demo, run `python -m http.server 8000` from the `frontend` directory.

---

## Local Development (without Docker)

1. Create a PostgreSQL user and database.

        sudo -u postgres createuser -P your_user
        sudo -u postgres createdb -O your_user your_database

2. Run the schema migration.

        psql -U your_user -d your_database -f src/main/resources/schema.sql

3. Configure the application.

        cp src/main/resources/application.properties.example src/main/resources/application.properties

   Edit the file with your credentials and JWT secret:

        spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
        spring.datasource.username=your_user
        spring.datasource.password=your_password
        app.jwt.secret=${JWT_SECRET}

   Set the environment variable for the secret:

        export JWT_SECRET="your_jwt_secret_key"

4. Build and run.

        mvn clean package
        java -jar target/ecommerce-0.1.0.jar

---

## Features

- **JWT + RBAC** – Secure authentication, salted password hashing, and `ADMIN` / `CUSTOMER` roles.
- **Product CRUD**
