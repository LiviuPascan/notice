# NoteKeeper API
NoteKeeper is a simple RESTful web application built with Java 17 and Spring Boot.  
It allows users to create, read, update, and delete personal notes.  
The application is backed by a PostgreSQL database.

This project demonstrates a clean architectural approach using layered structure (controller → service → repository), DTOs, validation, and production-ready coding practices.

## Features
### Implemented
- Java 17 + Spring Boot 3.5.0 base setup
- PostgreSQL integration using Spring Data JPA
- Note entity with JPA annotations
- CRUD repository for persistent storage
- REST controller exposing all CRUD endpoints
- Service layer containing business logic
- DTOs for request and response objects
- Bean validation using Jakarta Validation annotations
- Global exception handling with structured error responses
- Swagger / OpenAPI 3.1 documentation
- Swagger UI available at `/swagger-ui/index.html`
- API tested with Postman
- Authentication and authorization layer
- Unit and integration tests (JUnit + MockMvc)
- Docker containerization (Spring Boot + PostgreSQL)

### Planned
- Deployment to cloud platforms (Render, Railway, etc.)

## Technology Stack
- Java 17
- Spring Boot 3.5.0
- Spring Web
- Spring Data JPA (Hibernate)
- PostgreSQL
- Jakarta Validation
- Lombok
- Springdoc OpenAPI 2.x

## Project Structure
com.springliviu.notice
├── config                 # Configuration classes (security, password encoder, Swagger)
│   ├── PasswordEncoderConfig
│   ├── SecurityConfig
│   └── SwaggerConfig
├── controller             # REST controllers for authentication and note management
│   ├── AuthController
│   └── NoteController
├── dto                    # Data Transfer Objects (request/response payloads)
│   ├── AuthResponse
│   ├── LoginRequest
│   ├── RegisterRequest
│   ├── NoteRequest
│   └── NoteResponse
├── exception              # Global exception handling
│   └── GlobalExceptionHandler
├── model                  # JPA entity classes
│   ├── User
│   └── Note
├── repository             # Spring Data JPA repositories
│   ├── UserRepository
│   └── NoteRepository
├── security               # JWT utilities, authentication filters, user details
│   ├── CustomUserDetailsService
│   ├── JwtAuthFilter
│   └── JwtUtil
├── service                # Business logic and service layer
│   ├── AuthService
│   └── NoteService
└── NoticeApplication.java # Main application entry point


## Sample Request
POST /api/notes  
Content-Type: application/json  

{  
  "title": "Meeting notes",  
  "content": "Discuss project timeline and tasks"  
}

## Running the Application
1. Make sure PostgreSQL is running and accessible.  
2. Configure the database connection in `application.properties` or `application.yml`.  
3. Build and run the application using:  
   mvn clean install  
   mvn spring-boot:run  
4. Access Swagger UI at:  
   http://localhost:8080/swagger-ui/index.html

## License
This project is open-source and available under the MIT License.
