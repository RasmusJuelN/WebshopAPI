# WebshopAPI

A RESTful backend API for a web-based shopping platform built with **Spring Boot 3** and **Java 22**. The API supports product management with image uploads and user management with role-based access.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 22 |
| Framework | Spring Boot 3.3.3 |
| Build Tool | Maven |
| ORM | Spring Data JPA / Hibernate |
| Database | H2 (file-based, persistent) |
| Web | Spring MVC (REST) |
| Testing | JUnit 5 |

---

## Main Features

- **Product Management** – Create, read, update, and delete products including image uploads (stored as binary, returned as Base64)
- **User Management** – Register users and manage accounts with role-based access (`USER` / `ADMIN`)
- **User Authentication** – Login endpoint that validates credentials and returns the user's role
- **H2 Console** – Built-in database browser available at `/h2-console` for development

---

## Project Structure

```
src/
└── main/
    └── java/com/example/webshopapi/
        ├── WebshopApiApplication.java
        ├── controllers/
        │   ├── ProductController.java
        │   └── UserController.java
        ├── services/
        │   ├── ProductService.java
        │   └── UserService.java
        ├── repositories/
        │   ├── ProductRepository.java
        │   └── UserRepository.java
        ├── models/
        │   ├── Product.java
        │   └── User.java
        └── dto/
            ├── ProductDto.java
            └── UserDto.java
```

---

## API Endpoints

### Products – `/api/products`

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/products` | Get all products |
| `GET` | `/api/products/{id}` | Get a product by ID |
| `POST` | `/api/products` | Create a new product (`multipart/form-data`) |
| `PUT` | `/api/products/{id}` | Update an existing product (`multipart/form-data`) |
| `DELETE` | `/api/products/{id}` | Delete a product by ID |

**Product fields:** `name`, `price`, `stock`, `description`, `image` (file)

### Users – `/api/users`

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/users/login` | Authenticate a user; returns role or `"INVALID"` |
| `GET` | `/api/users` | Get all users |
| `GET` | `/api/users/{id}` | Get a user by ID |
| `POST` | `/api/users` | Create a new user |
| `DELETE` | `/api/users/{id}` | Delete a user by ID |

---

## Getting Started

### Prerequisites

- Java 22+
- Maven 3.x (or use the included `mvnw` wrapper)

### Run the application

```bash
./mvnw spring-boot:run
```

The API will start on `http://localhost:8080`.

### Run tests

```bash
./mvnw test
```

### H2 Database Console

While the application is running, the H2 browser console is available at:

```
http://localhost:8080/h2-console
```

| Setting | Value |
|---|---|
| JDBC URL | `jdbc:h2:file:/data/webshopdb` |
| Username | `sa` |
| Password | `Passw0rd` |

> **Note:** The JDBC URL must match the value of `spring.datasource.url` in `application.properties`. The default password is intended for local development only — change it and disable the H2 console (`spring.h2.console.enabled=false`) before deploying to any shared or production environment.

---

## Configuration

Key settings in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:file:/data/webshopdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```
