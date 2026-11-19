# EvenMad - Backend

REST API of [EvenMad](../README.md). It stores Madrid venues and their events (matches and concerts) in MongoDB, exposes them through documented endpoints, and includes the scrappers that obtain the events from the venues' official websites.

## Index

- [Tech stack and project structure](#tech-stack-and-project-structure)
  - [Tech stack](#tech-stack)
  - [Project structure](#project-structure)
  - [Architecture](#architecture)
- [Run application on local](#run-application-on-local)
  - [Previous requirements](#previous-requirements)
  - [Steps](#steps)
- [Configuration](#configuration)
  - [Environment variables (.env)](#environment-variables-env)
  - [application.properties](#applicationproperties)
  - [Profiles](#profiles)
- [API](#api)
  - [Documentation (Swagger)](#documentation-swagger)
  - [Endpoints](#endpoints)
  - [Response format](#response-format)
- [Data model](#data-model)
- [Scrappers](#scrappers)
- [Data seeder](#data-seeder)
- [Tests](#tests)
- [Known limitations](#known-limitations)

## Tech stack and project structure

### **Tech stack**

- Java 17
- Spring Boot 4.1.1 (built with Gradle Kotlin DSL)
- Spring Web MVC
- Spring Data MongoDB
- Validation
- Spring Boot Actuator
- Spring Boot DevTools
- SpringDoc OpenAPI (Swagger UI)
- Spring Dotenv (loads the `.env` file in development)
- jsoup (HTML scrapping)
- JUnit 5

### **Project structure**

```text
backend/
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew / gradlew.bat
├── .env.example                      # Template for the environment variables
└── src/
    ├── main/
    │   ├── java/lopez/noa/evenMad/
    │   │   ├── EvenMadApplication.java
    │   │   ├── configurations/       # OpenAPI config and development data seeder
    │   │   ├── constants/            # Application constants (venue names, date patterns)
    │   │   ├── controllers/          # REST controllers
    │   │   ├── services/             # Business logic
    │   │   ├── repositories/         # Spring Data MongoDB repositories
    │   │   ├── documents/            # MongoDB documents (Event, Venue, Category)
    │   │   │   └── subdocuments/     # Address, Details, MatchDetails, ConcertDetails
    │   │   ├── DTO/
    │   │   │   ├── creation/         # CreateEventDTO, CreateVenueDTO
    │   │   │   └── response/         # ResponseEventDTO, ResponseVenueDTO, ApiResponseDTO
    │   │   ├── mappers/              # Document <-> DTO conversion
    │   │   ├── scrappers/            # Web scrappers (one per source)
    │   │   └── utils/                # DataHelper, SlugHelper, ScrapperClient, MessagesHelper
    │   └── resources/
    │       ├── application.properties
    │       ├── application-dev.properties
    │       └── application-prod.properties
    └── test/java/lopez/noa/evenMad/
        └── EvenMadApplicationTests.java
```

### **Architecture**

The application follows a layered architecture:

```text
Controller  ->  Service  ->  Repository  ->  MongoDB
     \            |
      \-> DTO <-> Mapper <-> Document

Scrapper (jsoup) -> Service -> Repository -> MongoDB
```

- **Controllers** only receive the request and wrap the result in an `ApiResponseDTO`.
- **Services** hold the logic and use constructor injection.
- **Mappers** convert between documents and DTOs. Event titles and slugs are generated here.
- **Scrappers** read the external websites and create events through the services.

## Run application on local

### **Previous requirements**

- **JDK 17** (the Gradle toolchain is configured for Java 17).
- **MongoDB**: a local instance or a cloud database such as MongoDB Atlas, and its connection string.
- **Git**.

> [!NOTE]
> Gradle does not need to be installed: the project includes the Gradle Wrapper (`gradlew`).

### **Steps**

1. Clone the repository and enter the backend folder:

   ```bash
   git clone https://github.com/noaloprg/EvenMad.git
   cd EvenMad/backend
   ```

2. Create your `.env` file from the template and fill in your values (see [Configuration](#configuration)):

   ```bash
   cp .env.example .env
   ```

3. Run the application:

   ```bash
   # Linux / macOS
   ./gradlew bootRun

   # Windows
   gradlew.bat bootRun
   ```

4. Check that it works:

   - API: `http://localhost:8080/api/events/getAll`
   - Swagger UI: `http://localhost:8080/api/swagger-ui/index.html`
   - Health: `http://localhost:8080/api/actuator/health`

> [!WARNING]
> On every startup the [data seeder](#data-seeder) **deletes all events and venues** and inserts sample data. Do not point the application to a database with data you want to keep.

To build the executable JAR:

```bash
./gradlew build
java -jar build/libs/backend-0.0.1-SNAPSHOT.jar
```

> [!NOTE]
> The `.env` file is only loaded in development (`developmentOnly` dependency). When running the JAR, define the variables as real environment variables of the system.

## Configuration

The app uses `application.properties` for its configuration along with an `.env` file.

> [!NOTE]
> There's a file called `.env.example` where the `.env` keys are explained.

### **Environment variables (.env)**

| Key                | Description                                                  |
| ------------------ | ------------------------------------------------------------ |
| `MONGODB_URI`      | MongoDB connection string, including the password and the database name |
| `MONGODB_USERNAME` | MongoDB user name                                            |
| `MONGODB_PASSWORD` | MongoDB user password                                        |

Example:

```env
MONGODB_USERNAME=my_user
MONGODB_PASSWORD=my_password
MONGODB_URI=mongodb+srv://my_user:my_password@cluster0.example.mongodb.net/evenmad
```

> [!IMPORTANT]
> `.env` is ignored by Git. Never commit it.
> At the moment `application.properties` only reads `MONGODB_URI`.

### **Application.properties**

| Property                                     | Value                         | Description                                                    |
| -------------------------------------------- | ----------------------------- | -------------------------------------------------------------- |
| `spring.application.name`                    | `evenMad`                     | Application name                                               |
| `server.port`                                | `8080`                        | Server port                                                    |
| `server.servlet.context-path`                | `/api`                        | All endpoints are served under `/api`                          |
| `spring.mongodb.uri`                         | `${MONGODB_URI}`              | MongoDB connection string                                      |
| `spring.mongodb.field-naming-strategy`       | `SnakeCaseFieldNamingStrategy`| Field names are stored in snake_case in MongoDB                |
| `spring.data.web.pageable.default-page-size` | `20`                          | Default page size                                              |
| `spring.data.web.pageable.max-page-size`     | `100`                         | Maximum page size                                              |
| `spring.jackson.mapper.accept-case-insensitive-enums` | `true`               | Enums are accepted ignoring upper/lower case                   |

### **Profiles**

| Profile | File                          | Index creation | Logs                                     |
| ------- | ----------------------------- | -------------- | ---------------------------------------- |
| `dev`   | `application-dev.properties`  | Automatic      | `DEBUG` (MongoTemplate and Spring Web)   |
| `prod`  | `application-prod.properties` | Not automatic  | `ERROR` only                             |

No profile is active by default. To activate one:

```bash
SPRING_PROFILES_ACTIVE=dev ./gradlew bootRun
```

## API

Base URL: `http://localhost:8080/api`

### **Documentation (Swagger)**

| Resource      | URL                                              |
| ------------- | ------------------------------------------------ |
| Swagger UI    | `http://localhost:8080/api/swagger-ui/index.html` |
| OpenAPI JSON  | `http://localhost:8080/api/v3/api-docs`           |

### **Endpoints**

| Method | Path               | Description                                    |
| ------ | ------------------ | ---------------------------------------------- |
| `GET`  | `/events/getAll`   | Returns all events, each one with its venue ID |
| `GET`  | `/venues/getAll`   | Returns all venues                             |

### **Response format**

Every response is wrapped in the same structure:

```json
{
  "status": 200,
  "message": "List of events obtained correctly",
  "data": [
    {
      "id": "6740...",
      "title": "Atletico de Madrid vs FC Barcelona",
      "slug": "atletico-de-madrid-vs-fc-barcelona",
      "startTime": "2026-09-29T21:00:00",
      "endTime": "2026-09-29T22:55:00",
      "imageUrl": "imagenUrl",
      "venueId": "6740...",
      "details": {
        "type": "MATCH",
        "competition": "La Liga",
        "homeTeam": "Atletico de Madrid",
        "awayTeam": "FC Barcelona"
      }
    }
  ]
}
```

> [!NOTE]
> The example is abridged. `details` changes depending on the event category (see below).

## Data model

### **Event** (collection `events`)

| Field       | Type            | Notes                                                       |
| ----------- | --------------- | ----------------------------------------------------------- |
| `id`        | `String`        | Mongo `_id`                                                 |
| `title`     | `String`        | Generated from `details`. Text index                        |
| `slug`      | `String`        | Generated from the title. Unique index                      |
| `startTime` | `LocalDateTime` | Indexed (descending)                                        |
| `endTime`   | `LocalDateTime` |                                                             |
| `imageUrl`  | `String`        |                                                             |
| `venue`     | `String`        | ID of the venue it references. Indexed                      |
| `details`   | `Details`       | Polymorphic, depends on the category                        |

### **Details** (polymorphic)

The `type` property decides which subclass is used. Each subclass builds the event title.

| `type`    | Class            | Fields                                | Generated title                   |
| --------- | ---------------- | ------------------------------------- | --------------------------------- |
| `MATCH`   | `MatchDetails`   | `competition`, `homeTeam`, `awayTeam` | `Home vs Away`                    |
| `CONCERT` | `ConcertDetails` | `artist`, `tour`                      | `Artist - Tour`                   |

### **Venue** (collection `venues`)

| Field             | Type            | Notes                                         |
| ----------------- | --------------- | --------------------------------------------- |
| `id`              | `String`        | Mongo `_id`                                   |
| `name`            | `String`        | Text index                                    |
| `slug`            | `String`        | Unique index                                  |
| `capacity`        | `int`           |                                               |
| `affectedStreets` | `List<String>`  | Streets affected when the venue hosts an event |
| `location`        | `GeoJsonPoint`  | `2dsphere` index                              |
| `address`         | `Address`       | `street`, `city`, `postalCode`, `number`      |

Venues registered by the seeder: **Estadio Santiago Bernabéu** and **Riyadh Air Metropolitano**.

## Scrappers

Scrappers use [jsoup](https://jsoup.org/) to read the venues' websites. The URLs, the HTML containers and the date formats are documented in [`../docs/scrapping-sources.md`](../docs/scrapping-sources.md).

| Class                  | Role                                                                                      |
| ---------------------- | ----------------------------------------------------------------------------------------- |
| `EventScrapper`        | Interface that every scrapper implements                                                  |
| `BaseScrapper`         | Abstract class with shared logic (e.g. checking that the event belongs to the expected venue) |
| `MetropolitanoScrapper`| Reads men's and women's matches of Atlético de Madrid and keeps those played at the Metropolitano |
| `ScrapperClient` (utils)| Centralizes the HTTP connection (user agent, timeout)                                    |

> [!NOTE]
> `MetropolitanoScrapper.manageMatchDocuments()` already extracts matches and saves them, but nothing triggers it yet, and concert scrapping is not implemented.

## Data seeder

`DataSeederConfig` is a `CommandLineRunner` that runs on every startup. It:

1. Deletes all events and venues.
2. Creates the Bernabéu and Metropolitano venues.
3. Creates sample concerts and matches linked to those venues.

> [!WARNING]
> It is meant for the development stage only. Comment it out or restrict it to the `dev` profile before using a database with real data.

## Tests

```bash
./gradlew test
```

There is currently a single context-load test (`EvenMadApplicationTests`), so it needs a reachable MongoDB and a valid `MONGODB_URI`.

## Known limitations

- Only read endpoints (`GET`) are exposed; events and venues are created through the seeder or the scrappers.
- Scrappers are not scheduled or connected to the application yet.
- No filtering, search or pagination on the endpoints yet (page size properties are already configured).
- Error handling is still basic (generic exceptions marked with `REMEMBER` in the code).