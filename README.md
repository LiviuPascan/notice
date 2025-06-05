# NoteKeeper API

NoteKeeper is a simple RESTful web application built with Java 17 and Spring Boot.  
It allows users to create, read, update, and delete personal notes.  
The project is designed as a backend API and uses PostgreSQL as the data storage layer.

The goal of this project is to demonstrate clean architectural structure with proper layering (controller-service-repository), DTO usage, validation, and production-ready practices.

## Project Progress: NoteKeeper API

### Implemented
- Java 17 + Spring Boot 3.5.0 base project
- PostgreSQL database integration
- Note entity with JPA annotations
- CRUD repository for Note
- REST controller with full CRUD API
- Service layer with business logic (NoteService)
- DTOs for request and response models
- Bean validation for incoming requests
- Application tested via Postman

### Planned Features
- Global exception handler with custom error responses
- Swagger/OpenAPI documentation
- Security layer (authentication and authorization)
- Unit and integration testing (JUnit + MockMvc)
- Docker support (PostgreSQL + Spring Boot container)
- Deployment to Render, Railway, or similar platforms
 
