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
----
