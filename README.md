# Spring Boot Person Management API

This project is a demo Spring Boot RESTful API for managing `Person` entities, using MySQL for persistent storage and Redis for caching. It demonstrates basic CRUD operations, search/filter endpoints, pagination, and integration with Dockerized services.

## Features

- **Spring Boot** REST API
- **CRUD** operations for `Person` entity
- **MySQL** via Spring Data JPA
- **Redis** for caching person lookups by ID
- **Lombok** for boilerplate code reduction
- **API endpoints** for create, update, read (single & paginated), filter, and search
- **Postman**-friendly endpoints
- **Docker Compose**-ready setup for MySQL and Redis

---

## Prerequisites

- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/) or [Gradle](https://gradle.org/)
- [Docker](https://www.docker.com/) for local MySQL and Redis

---

## Getting Started

### 1. Clone the repository

```sh
git clone https://github.com/your-username/your-repo.git
cd your-repo
```

### 2. Run MySQL and Redis via Docker

#### MySQL

```sh
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=testdb -p 3306:3306 -d mysql:8
```

#### Redis

```sh
docker run --name redis -p 6379:6379 -d redis
```

### 3. Configure `application.yml`

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/testdb
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  redis:
    host: localhost
    port: 6379
```

### 4. Build & Run the Application

```sh
./mvnw spring-boot:run
# or
./gradlew bootRun
```

---

## API Endpoints

| Method | Endpoint                                 | Description                                      |
|--------|------------------------------------------|--------------------------------------------------|
| POST   | `/api/v1/persons`                        | Create new person                                |
| GET    | `/api/v1/persons/{id}`                   | Get person by ID (cached in Redis)               |
| PUT    | `/api/v1/persons/{id}`                   | Update person                                    |
| GET    | `/api/v1/persons`                        | Get all persons (with pagination)                |
| POST   | `/api/v1/persons/filter`                 | Filter persons by request body (optional/adv.)   |
| GET    | `/api/v1/persons/search?searchType=X&searchValue=Y` | Search persons by type/value (simulate cadre search) |

### Example: Create Person

```json
POST /api/v1/persons
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "voterCardNumber": "VC12345",
  "constituencyId": 1001
}
```

### Example: Search Person

```http
GET /api/v1/persons/search?searchType=firstName&searchValue=John
```

---

## Redis Caching

- The `GET /api/v1/persons/{id}` endpoint is cached using `@Cacheable`.
- To check cache keys in Redis:

```sh
docker exec -it redis redis-cli KEYS *
```

---

## Project Structure

```
src/
 └── main/
     ├── java/
     │    └── com.example.demo/
     │         ├── entity/Person.java
     │         ├── repository/PersonRepository.java
     │         ├── service/PersonService.java, PersonServiceImpl.java
     │         ├── controller/PersonController.java, PersonControllerImpl.java
     │         └── ...
     └── resources/
          └── application.yml
```

---

## Dependencies

- Spring Web
- Spring Data JPA
- MySQL Driver
- Redis
- Lombok
- Spring Boot DevTools

---

## Testing

- Use [Postman](https://www.postman.com/) to test API endpoints.
- Create, update, fetch persons and check Redis cache as described above.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Author

- [Your Name](https://github.com/your-username)
