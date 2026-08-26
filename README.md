# eCommerce
-------------

A full-stack e-commerce application that combines a lightweight HTML5 frontend with a robust Java Spring Boot backend, powered by a scalable PostgreSQL database.

[![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build)](https://github.com/shubhyagami/eCommerce/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/shubhyagami/eCommerce/blob/main/LICENSE)
[![Open Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce)](https://github.com/shubhyagami/eCommerce/issues)
[![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)](https://github.com/shubhyagami/eCommerce)

## Overview
-----------

Our e-commerce platform offers a standard online shopping experience with features like secure authentication, product cataloging, and cart management.

## Key Features
-------------

### Secure Authentication
------------------

- User login and registration using JSON Web Tokens (JWT) for a secure user experience.

### Product Catalog
-----------------

- RESTful API endpoints for product management and filtering
- Support for product variants and Stock-Keeping Units (SKUs)

### Responsive UI
----------------

- HTML5 layouts that adapt to mobile and desktop screens for a seamless user experience

### Cart Management
-----------------

- Session-based shopping cart persistence for a smooth shopping experience

### Transaction History
-------------------

- Database-backed order logging and user purchase history for efficient transaction tracking

## Getting Started
-----------------

### Prerequisites
----------------

- Java JDK 17 or later
- Maven
- PostgreSQL
- A modern web browser

### Setup Steps
-------------

1. **Clone the repository**: Download the latest version of the eCommerce repository to your local machine and navigate into it.
   ```bash
   git clone https://github.com/shubhyagami/eCommerce.git
   cd eCommerce
   ```

2. **Initialize the Database**: Restore the provided SQL schema to your local PostgreSQL instance.
   ```bash
   psql -U your_username -d your_database -f path/to/schema.sql
   ```

3. **Configure the Backend**: Update the `src/main/resources/application.properties` file with your database credentials and a secure JWT secret.
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   app.jwt.secret=your_secure_jwt_secret
   ```

4. **Run the Backend**: Build and launch the Spring Boot backend.
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Launch the Frontend**: Open the primary `index.html` file directly in your browser, or serve the directory using a local static server such as Live Server.

## Upcoming Developments
------------------------

Our next development priorities include:

- Fixing checkout exception handling (PR #42)
- Optimizing SKU indexing algorithms
- Integrating a multi-provider payment gateway
- Implementing an auto-scaling Redis cache layer
- Adding anti-bot/DDoS protection
- Moving towards a headless commerce architecture with a decoupled frontend

## Contributing
--------------

We welcome contributions to the eCommerce project! To get involved, fork the repository and create a feature branch for your changes. Submit a pull request with a clear description of your improvements and ensure that all tests pass.

## License
---------

eCommerce is licensed under the MIT License. See the [LICENSE file](LICENSE) for details.

### Recent Updates
------------------

* 2026-08-21: Refined README structure for better readability
* 2026-08-20: Verified build stability and passing GitHub Actions pipelines
* 2026-08-12: Pruned unused branches to prevent merge conflicts
* 2026-08-07: Isolated checkout branch anomaly (PR #42); optimization and bug fixes ongoing
