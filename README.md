# eCommerce

A full-stack e-commerce application featuring an HTML5 frontend, a Java Spring Boot backend, and a PostgreSQL database.

[![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build)](https://github.com/shubhyagami/eCommerce/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/shubhyagami/eCommerce/blob/main/LICENSE)
[![Open Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce)](https://github.com/shubhyagami/eCommerce/issues)
[![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)](https://github.com/shubhyagami/eCommerce)

## Overview

eCommerce is a web application that implements a standard online shopping experience. From product browsing to checkout, the platform combines a lightweight HTML5 frontend with a robust Java backend. It provides an extensible foundation for building a storefront.

### Features
- **Secure Authentication:** JWT-based user login and registration.
- **Product Catalog:** RESTful API endpoints for product management and filtering.
- **Responsive UI:** HTML5 layouts that adapt to mobile and desktop screens.
- **Cart Management:** Session-based shopping cart persistence.
- **Transaction History:** Database-backed order logging and user purchase history.

## Getting Started

Follow these instructions to set up a local copy of the project for development and testing.

### Prerequisites
- Java JDK 17 or later
- Maven
- PostgreSQL
- A modern web browser

### Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/shubhyagami/eCommerce.git
   cd eCommerce
   ```

2. **Initialize the Database:**
   Restore the provided SQL schema to your local PostgreSQL instance.
   ```bash
   psql -U your_username -d your_database -f path/to/schema.sql
   ```

3. **Configure the Backend:**
   Update the `src/main/resources/application.properties` file with your database credentials and a secure JWT secret.
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   app.jwt.secret=your_secure_jwt_secret
   ```

4. **Run the Backend:**
   Build and launch the Spring Boot backend:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Launch the Frontend:**
   Open the primary `index.html` file directly in your browser, or serve the directory using a local static server such as Live Server.

## Roadmap

- [x] Secure user authentication
- [x] Product cataloging and REST API endpoints
- [x] Responsive HTML5 UI layouts
- [x] Transaction history logging
- [x] Core database schema initialization
- [x] Session-based cart persistence
- [ ] Fix checkout exception handling (PR #42)
- [ ] Implement faster SKU indexing algorithms
- [ ] Multi-provider payment gateway integration
- [ ] Auto-scaling Redis cache layer
- [ ] Anti-bot/DDoS protection layer
- [ ] Headless commerce architecture with decoupled frontend

## Changelog

**2026-08-20**
- Refined README documentation for clarity and better navigation.
- Verified build stability and passing GitHub Actions pipelines.

**2026-08-12**
- Pruned unused branches to prevent merge conflicts.
- Updated project formatting standards.

**2026-08-07**
- Isolated checkout branch anomaly (PR #42); optimization and bug fixes ongoing.

## Contributing

Contributions are welcome! Please fork the repository and create a feature branch for your changes. Submit a pull request with a clear description of your improvements. Ensure all tests pass before submitting.

```bash
git checkout -b feature/AmazingFeature
git commit -m 'Add some AmazingFeature'
git push origin feature/AmazingFeature
```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
