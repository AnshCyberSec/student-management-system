# 🎓 Student Management System

A production-style backend application built using Spring Boot, Spring Security, JWT Authentication, JPA/Hibernate, and MySQL.

This project demonstrates how modern enterprise applications are designed and developed using industry-standard architecture, secure authentication mechanisms, role-based authorization, payment workflow management, pagination, sorting, global search, exception handling, and RESTful APIs.

---

# 🚀 Project Overview

Student Management System is a backend REST API application that allows administrators and users to manage student records securely.

The application provides complete student lifecycle management, secure user authentication using JWT, role-based access control, payment management, and advanced data retrieval capabilities such as pagination, sorting, and search.

The goal of this project is to simulate a real-world enterprise-grade backend application that follows industry best practices and clean architecture principles.

---

# 🏗️ Architecture

The application follows a layered architecture:

```text
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Database Layer
```

### Controller Layer

Handles incoming HTTP requests and returns API responses.

### Service Layer

Contains business logic and application workflows.

### Repository Layer

Communicates with the database using Spring Data JPA.

### Database Layer

Stores application data in MySQL.

---

# 🛠️ Tech Stack

## Backend

* Java 17
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate
* Maven

## Database

* MySQL

## Authentication

* JWT Access Token
* JWT Refresh Token
* BCrypt Password Encryption

## Utilities

* Lombok
* Bean Validation

## Version Control

* Git
* GitHub

---

# ✨ Features

## Student Management Module

### Student Registration

Create student records with validation.

### Student Update

Update existing student information.

### Student Deletion

Delete student records safely.

### Student Retrieval

Fetch individual student details.

### Student Listing

Retrieve all students with support for:

* Pagination
* Sorting
* Search

---

# 🔍 Global Search

Search students using:

* Student Code
* First Name
* Last Name
* Email

Example:

```http
GET /api/students?search=ansh
```

---

# 📄 Pagination

Retrieve records efficiently.

Example:

```http
GET /api/students?page=0&size=5
```

Response contains:

```json
{
  "currentPage": 0,
  "pageSize": 5,
  "totalElements": 25,
  "totalPages": 5,
  "hasNext": true,
  "hasPrevious": false
}
```

---

# ↕️ Sorting

Sort records dynamically.

Example:

```http
GET /api/students?sortBy=firstName&direction=asc
```

---

# 🔐 Authentication & Authorization

The application uses JWT-based authentication.

### Registration

Users can create accounts.

### Login

Users authenticate using username and password.

### Access Token

Short-lived token used for accessing protected APIs.

### Refresh Token

Used to generate a new access token without logging in again.

### Password Security

Passwords are encrypted using BCrypt before being stored in the database.

---

# 👤 Role Based Access Control

Two roles are supported:

```text
ADMIN
USER
```

### ADMIN Permissions

* Create Student
* Update Student
* Delete Student
* View Students
* Manage Payments

### USER Permissions

* View Student Data
* Access Authorized Endpoints

---

# 🔑 JWT Security Flow

```text
User Login
     ↓
Generate Access Token
     ↓
Generate Refresh Token
     ↓
Access Protected APIs
     ↓
Access Token Expired
     ↓
Refresh Token API
     ↓
Generate New Access Token
```

---

# 💳 Payment Management Module

A complete payment workflow has been implemented.

## Create Payment

Create a payment order.

### Status

```text
PENDING
```

---

## Payment Success

Mark payment as successful.

### Status

```text
SUCCESS
```

---

## Payment Failure

Mark payment as failed.

### Status

```text
FAILED
```

---

## Payment Tracking

Track complete payment lifecycle:

```text
PENDING
     ↓
SUCCESS

OR

PENDING
     ↓
FAILED
```

---

# 📚 REST API Endpoints

## Authentication APIs

### Register User

```http
POST /api/auth/register
```

### Login User

```http
POST /api/auth/login
```

### Refresh Access Token

```http
POST /api/auth/refresh-token
```

---

## Student APIs

### Create Student

```http
POST /api/students
```

### Get Student By Id

```http
GET /api/students/{id}
```

### Get All Students

```http
GET /api/students
```

### Update Student

```http
PUT /api/students/{id}
```

### Delete Student

```http
DELETE /api/students/{id}
```

---

## Payment APIs

### Create Payment

```http
POST /api/payments
```

### Payment Success

```http
POST /api/payments/success
```

### Payment Failed

```http
POST /api/payments/failed
```

### Get All Payments

```http
GET /api/payments
```

---

# ⚙️ Setup Instructions

## Clone Repository

```bash
git clone <repository-url>
```

## Navigate To Project

```bash
cd student-management-system
```

## Configure Database

Update:

```properties
application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management_system

spring.datasource.username=root

spring.datasource.password=your_password
```

---

## Run Application

```bash
mvn clean install

mvn spring-boot:run
```

Application starts at:

```text
http://localhost:9090
```

---

# 📂 Project Structure

```text
src
│
├── controller
├── service
├── service/impl
├── repository
├── entity
├── dto
├── security
├── exception
├── config
├── common
└── enums
```

---

# 🎯 Key Concepts Demonstrated

* REST API Development
* Spring Boot Architecture
* JWT Authentication
* Refresh Token Mechanism
* Role-Based Authorization
* Exception Handling
* Pagination
* Sorting
* Global Search
* Database Relationships
* Payment Workflow Management
* Secure Password Storage
* Clean Code Practices
* Layered Architecture

---

# 🔮 Future Enhancements

* Swagger/OpenAPI Documentation
* Docker Support
* Redis Caching
* Email Notifications
* Unit Testing
* Audit Logging
* Payment Gateway Integration
* File Upload Support

---

# 👨‍💻 Author

**Anshuman Dalabehera**

Backend Developer | Java | Spring Boot | Spring Security | JPA | MySQL

---

# ⭐ If you found this project useful, consider giving it a star.
