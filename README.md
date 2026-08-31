# eCommerce

A lightweight, full‑stack e‑commerce solution that combines an HTML5 front‑end with a Java Spring Boot back‑end and a scalable PostgreSQL database. It provides secure authentication, product cataloging, cart management, and detailed order history, delivering a smooth experience on both mobile and desktop devices.

[![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build)](https://github.com/shubhyagami/eCommerce/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/shubhyagami/eCommerce/blob/main/LICENSE)
[![Open Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce)](https://github.com/shubhyagami/eCommerce/issues)
[![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)](https://github.com/shubhyagami/eCommerce)

## Overview
A responsive online store built with Spring Boot that offers JWT‑based authentication, a RESTful product catalog, session‑aware cart handling, and persistent transaction records. The project emphasizes clean code, modular design, and easy extensibility, making it suitable for both learning and production use.

## Key Features
- **Secure Authentication** – JWT‑based login and registration with hashed passwords.  
- **Product Catalog** – RESTful endpoints for listing, filtering, and managing products, variants, and SKUs.  
- **Responsive UI** – HTML5 templates that adapt gracefully to different screen sizes.  
- **Cart Management** – Session‑aware cart that persists items across user interactions.  
- **Transaction History** – Detailed order logs and purchase history for each user.  

## Getting Started

### Prerequisites
- Java JDK 17 or later  
- Maven  
- PostgreSQL  
- A modern web browser (or a static server such as Live Server for the front‑end)

### Setup
1. **Clone the repository**
   ```bash
   git clone https://github.com/shubhyagami/eCommerce.git
   cd eCommerce
   ```

2. **Initialize the database**
   ```bash
   psql -U your_username -d your_database -f path/to/schema.sql
   ```

3. **Configure the back‑end** – edit `src/main/resources/application.properties` with your DB credentials, JWT secret, and any other required settings:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   app.jwt.secret=your_secure_jwt_secret
   ```

4. **Run the back‑end**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Start the front‑end**
   - Open `index.html` directly in a browser, or serve the folder with a static server like Live Server, or build and run it with any front‑end bundler of your choice.

### Development Tips
- Run unit and integration tests with `mvn test` to ensure stability before committing changes.  
- The CI workflow on GitHub Actions validates builds and test results on every push.  
- Keep feature branches short and focused; squash‑merge when ready to integrate.  

## Contributing
Contributions are welcome! Fork the repository, create a feature branch for your work, and submit a pull request with a clear description of your changes. Ensure all tests pass and follow the existing code style.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Recent Changes
- **2026‑08‑21**: Refined README structure for improved readability.  
- **2026‑08‑20**: Verified build stability; all GitHub Actions pipelines pass.  
- **2026‑08‑12**: Removed stale branches to prevent merge conflicts.  
- **2026‑08‑07**: Fixed checkout‑branch anomaly (PR #42) and applied related optimizations and bug fixes.  

---  

This README has been updated to reflect the current project status and to provide clearer guidance for contributors and users.
