# Microfeed

**Microfeed** is a microservices-based social feed application built with **Spring Boot**, **PostgreSQL**, **RabbitMQ**, and **Nginx** as an API gateway.  
Each microservice manages its own database, ensuring **scalability**, **modularity**, and **fault isolation**.

## 🚀 Features
- **Authentication Service** — Manages user registration and login.
- **User Profile Service** — Handles user profile creation, updates, and retrieval.
- **Post Service** — Allows users to create, update, and fetch posts.
- **RabbitMQ** — Enables asynchronous communication between microservices.
- **Nginx API Gateway** — Routes incoming HTTP requests to the appropriate microservice.
- **Dedicated PostgreSQL Database per Service** — Ensures strong separation of concerns.

---

## 🐳 Running with Docker Compose

### 1️⃣ Prerequisites

* Install **Docker** and **Docker Compose**
  [Get Docker](https://docs.docker.com/get-docker/)

### 2️⃣ Start All Services

```bash
docker-compose up --build
```

### 3️⃣ Access Services

* **Nginx API Gateway** → [http://localhost](http://localhost)
* **RabbitMQ Management** → [http://localhost:15672](http://localhost:15672) (user: `admin`, pass: `admin`)
* **PostgreSQL Databases** → Accessible via their respective ports:

  * Auth DB: `localhost:5433`
  * User Profile DB: `localhost:5434`
  * Post DB: `localhost:5435`

---

## 🛑 Stopping Services

```bash
docker-compose down
```

---

## 🧹 Clean Up (Remove Volumes & Images)

```bash
docker-compose down -v --rmi all
```