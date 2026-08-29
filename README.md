# eCommerce

An e‑commerce platform built with a lightweight HTML5 frontend, a robust Java Spring Boot backend, and a scalable PostgreSQL database.

[![Build Status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build)](https://github.com/shubhyagami/eCommerce/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/shubhyagami/eCommerce/blob/main/LICENSE)
[![Open Issues](https://img.shields.io/github/issues/shubhyagami/eCommerce)](https://github.com/shubhyagami/eCommerce/issues)
[![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)](https://github.com/shubhyagami/eCommerce)

## Overview
A full‑stack online store that provides secure authentication, product cataloging, cart management, and transaction history, delivering a responsive shopping experience across devices.

## Key Features
- **Secure Authentication** – JWT‑based login and registration to protect user accounts.  
- **Product Catalog** – RESTful API endpoints for managing and filtering products, including variants and SKUs.  
- **Responsive UI** – HTML5 layouts that adapt to mobile and desktop screens.  
- **Cart Management** – Session‑based shopping cart persistence for a seamless checkout flow.  
- **Transaction History** – Persistent order logging and purchase history for each user.  

## Getting Started
### Prerequisites
- Java JDK 17 or later  
- Maven  
- PostgreSQL  
- A modern web browser  

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

3. **Configure the backend** – edit `src/main/resources/application.properties` with your DB credentials and JWT secret.  
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   app.jwt.secret=your_secure_jwt_secret
   ```

4. **Run the backend**  
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Start the frontend** – open `index.html` directly in a browser or serve the folder with a static server such as Live Server.

## Contributing
Contributions are welcome! Fork the repository, create a feature branch, and submit a pull request with a clear description of your changes. Ensure all tests pass before merging.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Recent Changes
- 2026‑08‑21: Refined README structure for better readability.  
- 2026‑08‑20: Verified build stability and passing GitHub Actions pipelines.  
- 2026‑08‑12: Removed unused branches to avoid merge conflicts.  
- 2026‑08‑07: Addressed checkout branch anomaly (PR #42); optimization and bug fixes ongoing.
