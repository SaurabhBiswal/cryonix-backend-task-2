# Expert Consultation Backend Service

 **Live Demo**: `https://cryonix-backend-task-2-production.up.railway.app`

**Live Swagger UI**: `https://cryonix-backend-task-2-production.up.railway.app/swagger-ui.html`

This project is a Spring Boot REST API for searching and exploring available subject experts. It features JWT and Google OAuth2 authentication, PostgreSQL integration, and advanced API features like pagination and input validation.

## Features

- **Expert Search**: Search experts by specialization with keyword matching.
- **Expert Management**: Add new expert profiles with validation.
- **Security**: Secured with Spring Security, supporting both JWT (local login) and Google OAuth2.
- **Pagination**: Efficiently list experts using Spring Data's pagination.
- **Global Exception Handling**: Structured JSON error responses for all API failures.
- **Data Initialization**: Automatically seeds 5 sample expert profiles on startup.

## Technical Stack

- **Backend**: Spring Boot 3.4+ (Java 21)
- **Database**: PostgreSQL
- **Security**: Spring Security (JWT & OAuth2)
- **Validation**: Jakarta Validation API (Hibernate Validator)
- **Documentation**: README with AI Search Awareness

## Project Structure

```text
src/main/java/com/cryonix/expert/
├── config/             # Security and Data Initialization configuration
├── controller/         # REST Controllers (Experts, Auth)
├── dto/                # Data Transfer Objects (ErrorResponse)
├── entity/             # JPA Entities (ExpertProfile)
├── exception/          # Global Exception Handler
├── repository/         # Spring Data JPA Repositories
├── security/           # JWT and Authentication Filters
└── service/            # Business Logic Layer
```

## Setup Instructions

### 1. Prerequisites
- Java 21+
- PostgreSQL
- Maven

### 2. Database Configuration
Update `src/main/resources/application.yml` with your PostgreSQL credentials:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/expert_db
    username: your_username
    password: your_password
```

### 3. Google OAuth Setup
Obtain a Client ID and Client Secret from the [Google Cloud Console](https://console.cloud.google.com/) and set them as environment variables:
- `GOOGLE_CLIENT_ID`
- `GOOGLE_CLIENT_SECRET`

### 4. Run the Application
```bash
mvn spring-boot:run
```

## Authentication Approach

### JWT (Local testing)
1. **Endpoint**: `POST /api/auth/login`
2. **Credentials**: `username: admin`, `password: password`
3. **Usage**: Copy the returned `token` and add it as a `Bearer` token in the `Authorization` header for subsequent requests.

### Google OAuth2
- The application is configured to handle Google OAuth2 login via Spring Security's `oauth2Login()`.
- Unauthenticated users will be redirected to the Google login page.

## Database Schema

### Table: `ExpertProfile`
| Field | Type | Description |
|-------|------|-------------|
| id | BIGINT | Primary Key (Auto-increment) |
| name | VARCHAR | Full name of the expert |
| specialization | VARCHAR | Field of expertise |
| consultation_fee | DOUBLE | Fee per session |
| available | BOOLEAN | Availability status |

## API Demonstration & Testing

### Live Base URL
```
https://cryonix-backend-task-2-production.up.railway.app
```

### Interactive Documentation (Swagger)
Once the application is running, you can explore and test all endpoints interactively:
- **Live (Railway)**: `https://cryonix-backend-task-2-production.up.railway.app/swagger-ui.html`
- **Local**: `http://localhost:8080/swagger-ui.html`

### Postman Testing
- `GET /experts`: List all experts with pagination (`?page=0&size=5`).
- `GET /experts/search?q=keyword`: Search experts by specialization.
- `POST /experts`: Add a new expert (Requires JWT/OAuth).

## AI Search Awareness

### Improving Search with AI Embeddings
While the current search uses SQL `LIKE` queries, it is limited to exact keyword matches. Semantic search using AI embeddings (like OpenAI's `text-embedding-3-small` or local models via Ollama) can significantly improve the user experience:

1. **How it works**: Instead of keywords, expert specializations and user queries are converted into high-dimensional vectors (embeddings).
2. **Vector Database**: These vectors are stored in a vector database (e.g., pgvector for PostgreSQL).
3. **Similarity Search**: When a user searches for "design," the system finds the experts whose specializations are "semantically closest" to the query, even if the word "design" isn't explicitly mentioned (e.g., matching "UX Architect" or "Creative Director").
4. **Benefit**: Handles synonyms, typos, and concept-based searches much more effectively than traditional keyword matching.
