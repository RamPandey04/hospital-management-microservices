# Hospital Management System

A scalable and enterprise-inspired Hospital Management System built using Spring Boot Microservices architecture. The platform is designed to automate and streamline hospital operations including patient management, doctor management, appointment scheduling, medical records management, billing operations, notifications, and secure authentication.

The project demonstrates modern backend development practices including Microservices Architecture, API Gateway, Service Discovery, JWT Authentication, Kafka Event-Driven Communication, Redis Caching, Unit Testing, and Integration Testing.

---

## Overview

Hospital Management System provides a centralized healthcare platform where different hospital domains are developed as independent microservices.

The application allows hospitals to efficiently manage:

* Patients
* Doctors
* Appointments
* Medical Records
* Billing
* Notifications
* User Accounts

while maintaining scalability, maintainability, and separation of concerns.

---

## Architecture

```text
Client
   |
   v
API Gateway
   |
   +----------------------------------------------------------------+
   |         |         |          |          |          |            |
   v         v         v          v          v          v            v
Auth     User     Patient    Doctor   Appointment  Medical     Billing
Service  Service  Service    Service   Service     Record      Service
                                                        Service
                                                           |
                                                           |
                                                           v
                                                  Notification Service

Infrastructure

- Eureka Server
- PostgreSQL
- Redis
- Kafka
```

---

# Microservices

## Eureka Server

Responsible for service registration and discovery.

Features:

* Service Registration
* Service Discovery
* Dynamic Service Lookup

---

## API Gateway

Acts as the single entry point for all requests.

Features:

* Request Routing
* JWT Validation
* Centralized Security
* Global Filters
* Exception Handling

---

## Auth Service

Responsible for authentication and authorization.

Features:

* User Registration
* Login
* Logout
* Access Token Generation
* Refresh Token Generation
* Token Validation

Security:

* JWT Access Token
* JWT Refresh Token
* Redis Refresh Token Storage

Supported Roles:

* ADMIN
* DOCTOR
* RECEPTIONIST
* PATIENT

---

## User Service

Responsible for user profile management.

Features:

* Create User Profile
* Update User Profile
* View User Information
* Manage Roles

---

## Patient Service

Responsible for patient management.

Features:

* Register Patient
* Update Patient Information
* View Patient Details
* Search Patient
* Patient History

Patient Information:

* Name
* Age
* Gender
* Contact Number
* Address
* Blood Group
* Emergency Contact

---

## Doctor Service

Responsible for doctor management.

Features:

* Add Doctor
* Update Doctor Information
* Search Doctor
* Manage Availability
* Manage Specializations

Doctor Information:

* Name
* Specialization
* Experience
* Qualification

---

## Appointment Service

Responsible for appointment scheduling.

Features:

* Book Appointment
* Cancel Appointment
* Reschedule Appointment
* View Appointment History

Appointment Status:

* SCHEDULED
* COMPLETED
* CANCELLED

---

## Medical Record Service

Responsible for storing and managing patient medical records.

Features:

* Create Medical Records
* Update Medical Records
* View Medical History
* Treatment Tracking
* Diagnosis Tracking

Medical Record Data:

* Diagnosis
* Treatment
* Prescription
* Doctor Notes
* Visit History

---

## Billing Service

Responsible for hospital billing and payment management.

Features:

* Generate Bills
* Track Payments
* View Billing History
* Invoice Management

Billing Components:

* Consultation Charges
* Treatment Charges
* Laboratory Charges
* Medicine Charges

---

## Notification Service

Responsible for event notifications.

Features:

* Appointment Notifications
* Billing Notifications
* Registration Notifications
* Medical Record Update Notifications

Notification Types:

* Email Notifications
* In-App Notifications

---

# Event Driven Architecture

Apache Kafka is used for asynchronous communication between services.

### Appointment Created

Producer:

Appointment Service

Event:

```text
APPOINTMENT_CREATED
```

Consumer:

Notification Service

---

### Patient Registered

Producer:

Patient Service

Event:

```text
PATIENT_REGISTERED
```

Consumer:

Notification Service

---

### Medical Record Updated

Producer:

Medical Record Service

Event:

```text
MEDICAL_RECORD_UPDATED
```

Consumer:

Notification Service

---

### Bill Generated

Producer:

Billing Service

Event:

```text
BILL_GENERATED
```

Consumer:

Notification Service

---

# Security

The application uses JWT-based authentication and authorization.

Security Features:

* Access Token
* Refresh Token
* Gateway Level Token Validation
* Role-Based Authorization
* Redis Token Storage

Authentication Flow:

```text
Login
   |
Auth Service
   |
JWT Access Token
JWT Refresh Token
   |
API Gateway Validation
   |
Authorized Access
```

---

# Redis Usage

Redis is used for:

### Auth Service

* Refresh Token Storage
* Active Session Management

### Patient Service

* Frequently Accessed Patient Cache

### Appointment Service

* Frequently Accessed Appointment Cache

Benefits:

* Reduced Database Load
* Faster Response Time

---

# Technology Stack

## Backend

* Java 21
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Spring Cloud Gateway
* Spring Cloud Netflix Eureka

## Database

* PostgreSQL

## Messaging

* Apache Kafka

## Cache

* Redis

## Testing

* JUnit 5
* Mockito
* Spring Boot Test
* MockMvc

---

# Project Structure

```text
eureka-server

api-gateway

auth-service

user-service

patient-service

doctor-service

appointment-service

medical-record-service

billing-service

notification-service
```

---

# Testing Strategy

Every microservice includes:

## Unit Testing

Using:

* JUnit 5
* Mockito

Covered Layers:

* Controller Tests
* Service Tests
* Security Tests
* Filter Tests
* Exception Tests

---

## Integration Testing

Using:

* Spring Boot Test
* MockMvc

Covered Areas:

* API Endpoints
* Security Flow
* Database Integration
* Service Layer Integration

---

# Key Engineering Concepts Demonstrated

* Microservices Architecture
* API Gateway Pattern
* Service Discovery
* JWT Authentication
* Refresh Token Mechanism
* Role-Based Authorization
* Kafka Event-Driven Communication
* Redis Caching
* Distributed Service Communication
* Global Exception Handling
* DTO Pattern
* Clean Architecture
* Unit Testing
* Integration Testing

---

# Future Enhancements

* Laboratory Service
* Pharmacy Service
* Insurance Management
* Advanced Reporting
* Audit Logging
* Multi-Hospital Support

---

# Author

**Shriram Pandey**

Java Backend Developer

**Java | Spring Boot | Spring Security | Microservices | Kafka | Redis | PostgreSQL | JUnit | Mockito**
