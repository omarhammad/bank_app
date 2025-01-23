# Bank App Microservices

The **Bank App Microservices** project is a modular banking solution designed using the microservices architecture. It allows seamless scalability, high performance, and flexibility in managing accounts, loans, and cards. Each service is decoupled, ensuring easier maintenance and deployment.

---

## Table of Contents
- [Overview](#overview)
- [Key Features](#key-features)
- [Services](#services)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [API Endpoints](#api-endpoints)
- [Why Choose This Project?](#why-choose-this-project)
- [License](#license)
- [Contributors](#contributors)

---

## Overview
This project showcases the implementation of a scalable banking system where each service (e.g., Accounts, Loans, Cards) operates independently but collaborates to deliver comprehensive functionality. The use of **Spring Boot** and **Docker** ensures a modern, reliable, and efficient deployment setup.

---

## Key Features
- **Microservices Architecture**: Each service is modular, isolated, and independently deployable.
- **Database Integration**: PostgreSQL as the robust relational database backend.
- **API Documentation**: Swagger/OpenAPI for clear and interactive API documentation.
- **Containerization**: Fully Dockerized services for simplified deployment and scalability.
- **Gradle-Based Build**: Easy builds and dependency management.
- **RESTful APIs**: Clean and intuitive API design.

---

## Services
1. **Accounts Service**:
   - Manage customer accounts (create, update, delete).
   - Retrieve account details and balances.
   - Example Endpoint: `POST /accounts` to create a new account.

2. **Loans Service**:
   - Handle loan applications, repayments, and interest calculations.
   - Example Endpoint: `GET /loans/{customerId}` to retrieve loan details.

3. **Cards Service**:
   - Manage credit and debit cards.
   - Handle card issuance, transactions, and limits.
   - Example Endpoint: `POST /cards` to issue a new card.

---

## Technologies Used
- **Backend**: Java, Spring Boot
- **Database**: PostgreSQL
- **Build Tool**: Gradle
- **Containerization**: Docker
- **API Documentation**: SpringDoc OpenAPI (Swagger)
- **Monitoring**: Spring Actuator

---

## Setup and Installation
### Prerequisites
- Java 17+
- Docker
- Gradle (optional; Gradle Wrapper included)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/omarhammad/bank_app.git
   cd bank_app
   ```

2. Start the PostgreSQL database:
   ```bash
   docker-compose up -d
   ```

3. Build the project:
   ```bash
   ./gradlew clean build
   ```

4. Run specific services:
   - **Accounts Service**:
     ```bash
     ./gradlew :microservices:accounts:bootRun
     ```
   - **Loans Service**:
     ```bash
     ./gradlew :microservices:loans:bootRun
     ```
   - **Cards Service**:
     ```bash
     ./gradlew :microservices:cards:bootRun
     ```

---

## API Endpoints
### Accounts Service
- `POST /accounts`: Create a new account.
- `GET /accounts/{id}`: Retrieve account details.
- `DELETE /accounts/{id}`: Delete an account.

### Loans Service
- `POST /loans`: Apply for a loan.
- `GET /loans/{customerId}`: View loan details for a customer.

### Cards Service
- `POST /cards`: Issue a new card.
- `GET /cards/{id}`: Retrieve card details.
- `PUT /cards/{id}`: Update card limits.

Swagger documentation is available for each service:
- **Accounts**: `http://localhost:8081/docs`
- **Loans**: `http://localhost:8082/docs`
- **Cards**: `http://localhost:8083/docs`

---

## Why Choose This Project?
- **Scalable Architecture**: Add or remove services effortlessly.
- **Modern Technologies**: Uses industry-standard tools like Spring Boot, Docker, and PostgreSQL.
- **Developer-Friendly**: Provides clear API documentation and easy-to-use Gradle-based build systems.
- **Fast Deployment**: Dockerized setup ensures rapid deployment across environments.

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

## Contributors
- **Omar Hammad** - Developer and Maintainer
---