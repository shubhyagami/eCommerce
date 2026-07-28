╔══════════════════════════════════════════════════════════════╗
║                                                              ║
║   ███████╗ ██████╗ ██████╗ ███╗   ███╗███╗   ███╗███████╗  ║
║   ██╔════╝██╔════╝██╔═══██╗████╗ ████║████╗ ████║██╔════╝  ║
║   █████╗  ██║     ██║   ██║██╔████╔██║██╔████╔██║█████╗    ║
║   ██╔══╝  ██║     ██║   ██║██║╚██╔╝██║██║╚██╔╝██║██╔══╝    ║
║   ███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║██║ ╚═╝ ██║███████╗  ║
║   ╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚═╝     ╚═╝╚══════╝  ║
║                                                              ║
║   ╔═╗╔═╗╔═╗ ╔═╗╔═╗╔╗╔╔═╗╔═╗╔╦╗╦═╗╔═╗╔╦╗╔═╗                ║
║   ║ ║╚═╗║  ║ ║║ ║║║║║ ╦╠═╣ ║ ╠╦╝╠═╣ ║ ║ ║                ║
║   ╚═╝╚═╝╚═╝╚═╝╚═╝╝╚╝╚═╝╩ ╩ ╩ ╩╚═╩ ╩ ╩ ╚═╝                ║
║                                                              ║
║         🛒  Full-Stack E-Commerce Application  🛒             ║
║            HTML Frontend · Java Backend · SQL                ║
║                                                              ║
╚══════════════════════════════════════════════════════════════╝

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/shubhyagami/eCommerce/actions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Stars](https://img.shields.io/github/stars/shubhyagami/eCommerce?style=social)](https://github.com/shubhyagami/eCommerce)
[![Last Commit](https://img.shields.io/github/last-commit/shubhyagami/eCommerce)](https://github.com/shubhyagami/eCommerce/commits/main)
[![Repo Size](https://img.shields.io/github/repo-size/shubhyagami/eCommerce)](https://github.com/shubhyagami/eCommerce)

---

## 🚀 Project Description

**eCommerce** is a modern, full-stack web application that brings the complete online shopping experience to life. From browsing products to secure checkout, every step is crafted with clean HTML frontends and a robust Java backend. Whether you're a small business launching your first store or a developer looking for a reference architecture, this project is your blueprint for scalable e-commerce.

Built with simplicity and performance in mind, it uses **Java Servlets** and **JSP** for server-side logic, a **relational database** (MySQL/PostgreSQL) for persistence, and pure HTML/CSS/JavaScript for the client side. The application supports user authentication, product catalog management, shopping cart functionality, and order processing.

---

## 📦 Quick Start Guide

Initiate temporal deployment in your local environment with these sequential steps:

1. **Clone the Repository**
   ```bash
   git clone https://github.com/shubhyagami/eCommerce.git
   cd eCommerce
   ```

2. **Configure the Database**
   - Locate `src/main/resources/db.properties`.
   - Update the connection string, username, and password to match your local SQL instance.
   - Execute the provided `schema.sql` script to instantiate the database schema.

3. **Deploy via Apache Tomcat**
   - Build the project using Maven: `mvn clean package`
   - Deploy the resulting `target/eCommerce.war` to your Tomcat server (`webapps` directory).
   
4. **Launch the Application**
   - Navigate to `http://localhost:8080/eCommerce/` in your browser.
   - Default Administrator credentials are provided in `config.properties`. Change them immediately upon first login.

---

## 🔧 Tech Stack & Infrastructure

| Layer         | Technology                                 |
|---------------|--------------------------------------------|
| **Frontend**  | HTML5, CSS3, Vanilla JavaScript, Bootstrap |
| **Backend**   | Java 11+, Servlets, JSP                    |
| **Database**  | MySQL / PostgreSQL                         |
| **Server**    | Apache Tomcat 9+                           |
| **Build Tool**| Maven                                      |

---

## ✨ Weekly Highlight: The Temporal Cart System

This week's architectural spotlight focuses on the **Temporal Cart System**. Built to survive the heat death of a universe (or at least a session timeout), the cart service intelligently synchronizes guest and user states. 

When a variant user logs in, the TVA Temporal Loom seamlessly merges their guest cart items with their persistent database cart. No items are lost during the transition between timelines. This ensures a buttery-smooth checkout experience without duplicating quantities of the Infinity Stones.

---

## ⏳ Contributing – TVA Temporal Engineering Guidelines

Greetings, Variant Developer! Welcome to the **TVA Temporal Engineering Division** for Project eCommerce. Your contribution to this timeline is critical for maintaining the **Sacred Timeline** of clean code and bug-free checkouts. Before you submit your pull request, please follow these protocols:

### 🔧 Pruning the Branch
- All new features must be approved by the **Time-Keepers of Code Review**.  
- Fork the repository – consider this your personal **Tempo