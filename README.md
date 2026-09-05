# eCommerce

Lightweight full‑stack e‑commerce prototype written in **Java 17 Spring Boot** with a static front‑end built from plain HTML/CSS.  
It showcases JWT authentication, a RESTful product catalog, a session‑aware shopping cart, and a persistent order history stored in PostgreSQL.

[![Build status](https://img.shields.io/github/actions/workflow/status/shubhyagami/eCommerce/ci.yml?branch=main&label=build&style=flat-square)](https://github.com/shubhyagami/eCommerce/actions)
[![License](https://img.shields.io/github/license/shubhyagami/eCommerce?style=flat-square)](LICENSE)
[![Open issues](https://img.shields.io/github/issues/shubhyagami/eCommerce?style=flat-square)](https://github.com/shubhyagami/eCommerce/issues)
[![Stargazers](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)](https://github.com/shubhyagami/eCommerce/stargazers)

---

## Quick Start

    git clone https://github.com/shubhyagami/eCommerce.git
    cd eCommerce

Create a PostgreSQL user and database:

    sudo -u postgres createuser -P your_user
    sudo -u postgres createdb -O your_user your_database

Load the initial schema:

    psql -U your_user -d your_database -f src/main/resources/schema.sql

Copy the example configuration and edit it:

    cp src/main/resources/application.properties.example src/main/resources/application.properties
    # Edit DB credentials and set a JWT secret

Build and launch:

    mvn clean package
    java -jar target/ecommerce-0.1.0.jar

Open <http://localhost:8080> and register a new user to explore the application.  
**NOTE** – Keep the JWT secret confidential; use an environment variable `JWT_SECRET` or a secrets manager in production.

---

## Core Features

- **Secure authentication** – JWT tokens, salted password hashing, role‑based access control  
- **RESTful product API** – CRUD endpoints for categories, products, variants, and SKUs  
- **Session‑aware cart** – Cart data persisted in the database, survives browser restarts and tab changes  
- **Order history** – Per‑user transaction records, exportable to CSV or PDF  
- **Responsive UI** – Vanilla HTML5 & CSS, mobile‑first design, no JavaScript frameworks  
- **CI‑driven** – Automated builds, unit and integration tests on every push

---

## Tech Stack

| Layer        | Technology                                             |
|--------------|--------------------------------------------------------|
| **Backend**  | Java 17, Spring Boot, Spring Data JPA, Spring Security, JWT |
| **Frontend** | Plain HTML5 & CSS (no frameworks)                      |
| **Database** | PostgreSQL                                            |
| **Build**    | Maven                                                  |
| **CI**       | GitHub Actions                                        |

---

## Prerequisites

- Java 17 (JDK or JRE)
- Maven 3.9+
- PostgreSQL 13+
- Git
- Modern web browser (Chrome, Firefox, Edge)

---

## Configuration

Create `src/main/resources/application.properties` from the example and fill in your details:

    # PostgreSQL
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
    spring.datasource.username=your_user
    spring.datasource.password=your_password

    # JWT
    app.jwt.secret=${JWT_SECRET}

**Security tip** – Never commit a hard‑coded JWT secret. In production, set the `JWT_SECRET` environment variable or use a secrets manager.

---

## Running Locally

    mvn clean package
    java -jar target/ecommerce-0.1.0.jar

The REST API is available at <http://localhost:8080>.

---

## Serving the Front‑End

The UI is a static site. One quick way to serve it:

    python -m http.server 8000

Open <http://localhost:8000> – the front‑end automatically points to the API at <http://localhost:8080>.

---

## Testing

    mvn test

All unit and integration tests run automatically in the GitHub Actions workflow on every push.

---

## Development Guidelines

- Keep feature branches focused (e.g., `feature/add-coupon-system`).  
- Squash commits before PRs.  
- Run `mvn spotless:apply` to format and lint code.  
- Update `schema.sql` whenever JPA entity mappings change.  
- Verify all tests pass locally before pushing.

---

## Contributing

1. Fork the repository.  
2. Create a short‑lived feature branch: `git checkout -b feature/awesome`.  
3. Commit your changes.  
4. Push: `git push origin feature/awesome`.  
5. Open a pull request with a concise description.  

Run the tests locally and confirm the CI status is green before submitting.

---

## License

MIT – see the LICENSE file for details.

---

## Changelog

| Date       | Change |
|------------|--------|
| 2026‑09‑01 | Refactored README, streamlined sections |
| 2026‑08‑21 | Minor wording tweaks |
| 2026‑08‑20 | Updated CI badge, fixed table formatting |
