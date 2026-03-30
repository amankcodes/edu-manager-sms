-- ============================================================
-- Edu Manager SMS – Database Schema
-- ============================================================

CREATE DATABASE IF NOT EXISTS future_edu_manager;
USE future_edu_manager;

-- Users table (students, faculty, admins)
CREATE TABLE IF NOT EXISTS users (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100)  NOT NULL,
    email    VARCHAR(150)  NOT NULL UNIQUE,
    password VARCHAR(255)  NOT NULL,
    phone    VARCHAR(20),
    course   VARCHAR(100),
    year     VARCHAR(20),
    address  TEXT,
    photo    VARCHAR(255),
    role     ENUM('ADMIN', 'FACULTY', 'STUDENT') NOT NULL DEFAULT 'STUDENT'
);

-- Attendance table
CREATE TABLE IF NOT EXISTS attendance (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id      BIGINT       NOT NULL,
    subject         VARCHAR(100) NOT NULL,
    total_classes   INT          NOT NULL DEFAULT 0,
    present_classes INT          NOT NULL DEFAULT 0,
    CONSTRAINT fk_attendance_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Fees table
CREATE TABLE IF NOT EXISTS fees (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id   BIGINT NOT NULL,
    total_amount INT    NOT NULL DEFAULT 0,
    paid_amount  INT    NOT NULL DEFAULT 0,
    CONSTRAINT fk_fees_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Grades table
CREATE TABLE IF NOT EXISTS grade (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id      BIGINT       NOT NULL,
    subject         VARCHAR(100) NOT NULL,
    exam_type       VARCHAR(50),
    marks_obtained  DOUBLE       NOT NULL DEFAULT 0,
    total_marks     DOUBLE       NOT NULL DEFAULT 0,
    CONSTRAINT fk_grade_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE
);
