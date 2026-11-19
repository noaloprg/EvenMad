# EvenMad

EvenMad gathers the events that take place in Madrid's big venues and exposes them in a clean, structured way. Each venue also stores the **streets affected** when it hosts an event, so the data can be used to know where and when the city gets busy.

> [!NOTE]
> The project is **in active development**. Right now the repository contains the backend only. See [Project status](#project-status) for what is already working and what is still pending.

## Index

- [What is EvenMad?](#what-is-evenmad)
- [Monorepo structure](#monorepo-structure)
- [Project status](#project-status)
- [Data model](#data-model)
- [Data sources (scrapping)](#data-sources-scrapping)
- [Getting started](#getting-started)
- [Roadmap](#roadmap)
- [Author](#author)

## What is EvenMad?

Madrid hosts matches and concerts every week, and the information is spread over many websites with different formats. EvenMad:

1. **Scraps** the official websites of each venue.
2. **Normalizes** the data into a common model (`Event` + `Venue`).
3. **Stores** it in MongoDB.
4. **Exposes** it through a documented REST API.

Supported event categories:

| Category  | Description                                    |
| --------- | ---------------------------------------------- |
| `MATCH`   | Sport matches (competition, home and away team) |
| `CONCERT` | Concerts (artist and tour)                     |

## Monorepo structure

```text
EvenMad/
├── backend/            # Java 17 + Spring Boot REST API (see backend/README.md)
├── data_structure/     # Draft JSON examples of the documents stored in MongoDB
│   ├── events/         #   concert.json, match.json
│   └── venues/         #   metropolitano.json
├── docs/
│   └── scrapping-sources.md   # Source URLs and HTML structure used by each scrapper
├── .vscode/            # Recommended editor extensions
└── README.md           # This file
```

| Module                                   | Description                                                | Docs                                        |
| ---------------------------------------- | ---------------------------------------------------------- | ------------------------------------------- |
| [`backend`](./backend)                   | REST API, MongoDB persistence and web scrappers            | [backend/README.md](./backend/README.md)    |
| [`data_structure`](./data_structure)     | Reference JSON of the data model (design drafts)           | -                                           |
| [`docs`](./docs)                         | Documentation about the sources the data is extracted from | [scrapping-sources.md](./docs/scrapping-sources.md) |

## Project status

### Done

- REST API with Spring Boot, MongoDB persistence and OpenAPI/Swagger documentation.
- Data model for `Event` and `Venue`, with polymorphic event details (`MATCH` / `CONCERT`).
- Read endpoints: list all events and list all venues.
- Automatic generation of titles and slugs for events and venues.
- Development data seeder with two venues (Santiago Bernabéu and Riyadh Air Metropolitano) and sample events.
- Scrapper base architecture (`EventScrapper` interface, `BaseScrapper`, `ScrapperClient`).
- Scrapper for **Metropolitano matches** (men's and women's first team of Atlético de Madrid).

### In progress / pending

- Connecting the scrappers to the application so they run automatically (the `MetropolitanoScrapper` is not yet triggered by anything).
- Scrapper for Metropolitano **concerts**.
- Scrappers for other venues (e.g. Santiago Bernabéu).
- Event images: where to get them from and where to store them.
- Error handling and custom exceptions (several places are marked with `REMEMBER` in the code).
- Filtering, search and pagination on the endpoints.

## Data model

There are two collections in MongoDB. Events point to their venue by ID.

```text
venues                          events
──────                          ──────
_id                             _id
name        (text index)        title       (text index, generated from details)
slug        (unique)            slug        (unique, generated from title)
capacity                        startTime   (indexed)
affectedStreets[]               endTime
location    (GeoJSON 2dsphere)  imageUrl
address {street, city,          venue       (venue _id, indexed)
         postalCode, number}    details     (polymorphic, see below)
```

`details` depends on the event category and carries a `type` discriminator:

```jsonc
// MATCH
{ "type": "MATCH", "competition": "LaLiga", "homeTeam": "Atlético de Madrid", "awayTeam": "Real Madrid" }

// CONCERT
{ "type": "CONCERT", "artist": "The Weeknd", "tour": "After Hours Til Dawn" }
```

> [!NOTE]
> The JSON files in [`data_structure/`](./data_structure) are early design drafts. The source of truth for the model is the code in `backend/src/main/java/lopez/noa/evenMad/documents`.

## Data sources (scrapping)

All the information comes from the official websites of the venues. The URLs, the HTML containers and the format of the dates of each source are documented in [`docs/scrapping-sources.md`](./docs/scrapping-sources.md).

| Venue                         | Content  | Source                                                             | Status      |
| ----------------------------- | -------- | ------------------------------------------------------------------ | ----------- |
| Riyadh Air Metropolitano      | Matches  | `atleticodemadrid.com` (men's and women's calendar)                | Implemented |
| Riyadh Air Metropolitano      | Concerts | `riyadhairmetropolitano.com/programacion`                          | Documented  |

> [!IMPORTANT]
> Scrappers depend on the HTML structure of third-party websites. If a website changes its layout, the scrapper will need to be updated.

## Getting started

The backend is the only runnable module for now. Full instructions (requirements, configuration and environment variables) are in [`backend/README.md`](./backend/README.md).

Quick start:

```bash
git clone https://github.com/noaloprg/EvenMad.git
cd EvenMad/backend

# create your environment file and fill in your MongoDB connection string
cp .env.example .env

./gradlew bootRun
```

Once running:

- API base URL: `http://localhost:8080/api`
- Swagger UI: `http://localhost:8080/api/swagger-ui/index.html`

## Roadmap

- [ ] Run scrappers automatically (scheduled) and avoid duplicated events.
- [ ] Concerts scrapper for the Metropolitano.
- [ ] Add more Madrid venues.
- [ ] Endpoints to filter events by venue, category and date range.
- [ ] Frontend to browse the events.

## Author

Developed by **Noa López** ([@noaloprg](https://github.com/noaloprg)).