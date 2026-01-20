# Edu Manager - Student Management System (Backend)

A Spring Boot based backend application built to manage student academic and administrative workflows such as enrollment, attendance, fees, and grades. The project follows layered architecture (Controller → Service → Repository) and exposes REST APIs for CRUD operations with validation and structured responses.

## 🚀 Tech Stack
- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Postman
- Git/GitHub

## ✨ Key Features
- Role-based backend structure (Admin/User flow)
- REST APIs for:
  - Student Enrollment
  - Attendance Tracking
  - Grade Management
  - Fee Records
- Service-layer business logic implementation
- Database integration using Spring Data JPA
- Exception handling & meaningful HTTP status responses
- API testing using Postman

## 🏗️ Project Architecture


## ⚙️ Setup & Run Locally

### 1) Clone the repository
```bash
git clone https://github.com/amankcodes/edu-manager-sms.git
cd edu-manager-sms

CREATE DATABASE edu_manager_db;


spring.datasource.url=jdbc:mysql://localhost:3306/edu_manager_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD


mvn clean install
mvn spring-boot:run

app will start on:
http://localhost:8080
