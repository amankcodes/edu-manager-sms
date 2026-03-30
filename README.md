# Edu Manager - Student Management System (Backend)

A Spring Boot based backend application built to manage student academic and administrative workflows such as enrollment, attendance, fees, and grades. The project follows a layered architecture (Controller → Repository) and exposes REST APIs for CRUD operations with structured responses.

## 🚀 Tech Stack
- Java 17
- Spring Boot 3.2.5
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Postman
- Git/GitHub

## ✨ Key Features
- Role-based backend structure (Admin / Faculty / Student)
- REST APIs for:
  - Student profile management
  - Attendance tracking (full CRUD)
  - Grade management (full CRUD)
  - Fee records (full CRUD)
- Profile photo upload & retrieval
- Global exception handling with structured error responses
- Database integration using Spring Data JPA

## 🏗️ Project Architecture

```
src/main/java/com/futureedu/manager/
├── controller/        REST API controllers
│   ├── AuthController.java
│   ├── AttendanceController.java
│   ├── FeesController.java
│   ├── GradeController.java
│   ├── ProfilePhotoController.java
│   ├── StudentController.java
│   └── StudentDataController.java
├── exception/
│   └── GlobalExceptionHandler.java
├── model/             JPA entities
│   ├── Attendance.java
│   ├── Fees.java
│   ├── Grade.java
│   └── User.java
├── repository/        Spring Data JPA interfaces
│   ├── AttendanceRepository.java
│   ├── FeesRepository.java
│   ├── GradeRepository.java
│   └── UserRepository.java
└── FutureEduManagerApplication.java
```

## 🗄️ Database Setup

### 1) Create the database

Run the provided schema file:
```bash
mysql -u root -p < src/main/resources/schema.sql
```

Or manually create the database and let Hibernate auto-create the tables by temporarily setting `spring.jpa.hibernate.ddl-auto=update` in `application.properties`.

### 2) Configure `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/future_edu_manager
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=none
```

## ⚙️ Setup & Run Locally

### 1) Clone the repository
```bash
git clone https://github.com/amankcodes/edu-manager-sms.git
cd edu-manager-sms
```

### 2) Configure the database
Update `src/main/resources/application.properties` with your MySQL credentials.

### 3) Build & run
```bash
mvn clean install
mvn spring-boot:run
```

The app will start on: **http://localhost:8080**

## 📡 API Reference

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/login` | Login with email & password |

### Student Profile
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/student/profile/{id}` | Get student profile |
| PUT | `/api/student/profile/{id}` | Update student profile |

### Attendance
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/student/attendance/{studentId}` | Get all attendance records for a student |
| POST | `/api/student/attendance` | Add an attendance record |
| PUT | `/api/student/attendance/{id}` | Update an attendance record |
| DELETE | `/api/student/attendance/{id}` | Delete an attendance record |

### Fees
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/student/fees/{studentId}` | Get fees for a student |
| POST | `/api/student/fees` | Add a fee record |
| PUT | `/api/student/fees/{id}` | Update a fee record |
| DELETE | `/api/student/fees/{id}` | Delete a fee record |

### Grades
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/student/grades/{studentId}` | Get all grade records for a student |
| POST | `/api/student/grades` | Add a grade record |
| PUT | `/api/student/grades/{id}` | Update a grade record |
| DELETE | `/api/student/grades/{id}` | Delete a grade record |

### Profile Photo
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/student/photo/{id}` | Upload profile photo |
| GET | `/api/student/photo/view/{id}` | View profile photo |

