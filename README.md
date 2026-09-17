[K[2m  [2mmodel openai/gpt-oss-20b failed, trying next...[0m[0m
[K[2m  [2mmodel openai/gpt-oss-120b failed, trying next...[0m[0m
# eCommerce

A lightweight, full-stack e-commerce prototype built with **Java 17** and **Spring Boot 3**. 

This project provides a robust back-end REST API for managing products, categories, variants, and orders, paired with a clean, vanilla HTML/CSS front-end. The architecture demonstrates industry-standard implementations of JWT authentication, role-based access control (RBAC), Spring Data JPA, and PostgreSQL.

![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)  
![Test status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=test&style=flat-square)  
![Coverage](https://img.shields.io/codecov/c/github/shubhyagami/eCommerce?style=flat-square)  
![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)  
![Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)  
![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)

---

## Quick Start

To get the application up and running quickly:

```bash
# Clone the repository
git clone https://github.com/shubhyagami/eCommerce.git
cd eCommerce

# Build and run the Spring Boot application
mvn clean package
java -jar target/ecommerce-0.1.0.jar
```

The API will be available at `http://localhost:8080`. To launch the static front-end:

```bash
cd frontend
python -m http.server 8000
```
Then, open `http://localhost:8000` in your browser.

---

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Docker Setup](#docker-setup)
  - [Manual Local Setup](#manual-local-setup)
- [Features](#features)
- [Testing](#testing)
- [Development Guidelines](#development-guidelines)
- [Contributing](#contributing)
- [License](#license)
- [Changelog](#changelog)

---

## Getting Started

### Prerequisites

- **Java 17** (JDK)
- **Maven 3.9+**
- **PostgreSQL 13+**
- **Git**
- **Docker** (Optional, recommended for fast setup)

### Docker Setup

The easiest way to start the project is using the provided Docker Compose file, which orchestrates both the API and the database.

```bash
docker compose up -d
```
Instances will be available at:
- **API**: `http://localhost:8080`
- **PostgreSQL**: `localhost:5432`

### Manual Local Setup

If you prefer running the services natively:

1. **Database Preparation**
   ```bash
   sudo -u postgres createuser -P your_user
   sudo -u postgres createdb -O your_user your_database
   ```

2. **Initialize Schema**
   ```bash
   psql -U your_user -d your_database -f src/main/resources/schema.sql
   ```

3. **Application Configuration**
   Copy the example properties file:
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```
   Update `application.properties` with your credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_user
   spring.datasource.password=your_password
   app.jwt.secret=${JWT_SECRET}
   ```
   *Note: For security, avoid committing hard-coded secrets. Set `JWT_SECRET` as an environment variable in production.*

4. **Launch**
   ```bash
   mvn clean package
   java -jar target/ecommerce-0.1.0.jar
   ```

---

## Features

| Feature | Description |
| :--- | :--- |
| **JWT Authentication** | Secure login with salted password hashing and role-based access control. |
| **Product Management** | Full RESTful CRUD API for categories, products, variants, and SKUs. |
| **Persistent Cart** | User-aware shopping cart that persists across browser sessions. |
| **Order Processing** | Order history tracking with support for CSV and PDF exports. |
| **Responsive UI** | Mobile-first front-end built with clean HTML5 and CSS. |
| **Automated CI/CD** | GitHub Actions pipeline for automated builds, testing, and coverage. |

---

## Testing

Run the full suite of unit and integration tests using Maven:

```bash
mvn test
```
All tests are automatically validated on every push via GitHub Actions to ensure stability.

---

## Development Guidelines

To maintain code quality, please follow these standards:

- **Branching**: Use `feature/...`, `bugfix/...`, or `chore/...` prefixes.
- **Commits**: Keep commits atomic and focused. Squash commits before creating a Pull Request.
- **Formatting**: Run `mvn spotless:apply` to ensure consistent code style.
- **Database**: Update `schema.sql` whenever entity mappings or table structures change.
- **CI/CD**: Ensure all tests pass locally and the CI pipeline is green before requesting a merge.

---

## Contributing

1. Fork the repository.
2. Create your feature branch: `git checkout -b feature/your-feature-name`.
3. Commit your changes and push to your fork.
4. Open a Pull Request with a clear description of the changes.
5. Ensure all tests pass and the CI status is green.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Changelog

| Date | Change |
| :--- | :--- |
| 2026-09-17 | Polished README for clarity and improved developer onboarding. |
| 2026-09-01 | Refactored README structure and streamlined sections. |
| 2026-08-21 | Minor wording and documentation tweaks. |
| 2026-08-20 | Updated CI badges and fixed table formatting. |
