<h1 align="center">eCommerce</h1>
<p align="center">A full-stack e-commerce application built with an HTML5 frontend, Java Spring Boot backend, and PostgreSQL database.</p>

<p align="center">
  <a href="https://github.com/shubhyagami/eCommerce/actions/workflows/ci.yml"><img src="https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build" alt="Build Status"></a>
  <a href="https://github.com/shubhyagami/eCommerce/blob/main/LICENSE"><img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="License: MIT"></a>
  <a href="https://github.com/shubhyagami/eCommerce/issues"><img src="https://img.shields.io/github/issues/shubhyagami/eCommerce" alt="Open Issues"></a>
  <a href="https://github.com/shubhyagami/eCommerce"><img src="https://img.shields.io/github/repo-size/shubhyagami/eCommerce" alt="Repo Size"></a>
  <a href="https://github.com/shubhyagami/eCommerce"><img src="https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social" alt="Stars"></a>
</p>

---

## Overview

**eCommerce** is a full-stack web application providing a complete online shopping experience. From product browsing to checkout, the platform combines clean HTML5 frontends with a robust Java backend. It serves as a solid, extensible foundation for a standard storefront.

### Features
- **Secure Authentication:** JWT-based user login and registration.
- **Product Cataloging:** RESTful API endpoints for product management and filtering.
- **Responsive UI:** HTML5 layouts that adapt to both mobile and desktop screens.
- **Cart Management:** Session-based shopping cart persistence.
- **Transaction History:** Database-backed order logging and user purchase history.

---

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

2. **Database Initialization:**
   Restore the provided SQL schema to your local PostgreSQL instance.
   ```bash
   psql -U your_username -d your_database -f path/to/schema.sql
   ```

3. **Backend Configuration:**
   Update the `src/main/resources/application.properties` file with your database credentials and a secure JWT secret.
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   app.jwt.secret=your_secure_jwt_secret
   ```

4. **Compile and Run the Backend:**
   Execute the following commands to build and launch the Spring Boot backend:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Launch the Frontend:**
   Open the primary `index.html` file directly in your browser, or serve the directory using a local static server.

---

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
- [ ] LLM-powered product help chatbot
- [ ] Anti-bot/DDoS protection layer
- [ ] Headless commerce architecture with decoupled frontend

---

## Changelog

**2026-08-07**
- Verified build stability and passing GitHub Actions pipelines.
- Cleaned documentation and updated formatting standards.
- Isolated checkout branch anomaly (PR #42); optimization and bug fixes ongoing.
- Pruned unused branches to prevent merge conflicts.

---

## Contributing

Contributions are welcome! Please fork the repository and create a feature branch for your changes. Submit a pull request with a clear description of your improvements. Ensure all tests pass before submitting.

```bash
git checkout -b feature/AmazingFeature
git commit -m 'Add some AmazingFeature'
git push origin feature/AmazingFeature
```

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
