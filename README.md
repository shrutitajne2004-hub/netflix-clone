# Netflix Clone

A full-stack Netflix clone featuring user authentication, movie browsing, watchlist management, and reviews. Built with a Spring Boot backend and a React frontend, leveraging Redis for caching to improve performance.

## 🔗 Live Demo

- **App:** [https://netflix-clone-zeta-two-47.vercel.app](https://netflix-clone-zeta-two-47.vercel.app)
- **API Docs (Swagger):** [https://netflix-clone-1-zr5z.onrender.com/swagger-ui/index.html](https://netflix-clone-1-zr5z.onrender.com/swagger-ui/index.html)

> **Note:** This project runs on free-tier hosting (Render, Aiven MySQL, Upstash Redis). A keep-alive ping runs every 10 minutes to minimize cold starts, but the first load may occasionally take 10-20 seconds if the services have been idle.

## Features

- **User Authentication:** JWT-based login and registration.
- **Movie Catalog:** Browse and search through a catalog of 45+ movies.
- **Watchlist:** Add or remove movies from your personal watchlist.
- **Reviews:** Read and submit reviews for movies, with live-calculated average ratings.
- **More Like This:** Genre-based movie recommendations on each movie's details page.
- **Caching:** Redis caching for optimized performance, with proper cache invalidation on data changes.
- **API Documentation:** Swagger UI for easy API testing and exploration.

## Tech Stack

- **Backend:** ![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot) ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
- **Frontend:** ![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
- **Database:** ![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
- **Cache:** ![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?&style=for-the-badge&logo=redis&logoColor=white)
- **Security:** JWT (JSON Web Tokens)
- **Deployment:** ![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white) Render (backend), Vercel (frontend), Aiven (MySQL), Upstash (Redis)

## Screenshots

<!-- Add screenshots here -->
> *Screenshot placeholders - to be added*

## Getting Started (Local Development)

### Prerequisites

- Node.js and npm
- Java 17+ and Maven
- MySQL Server
- Docker (for running Redis locally)

### Backend Setup

1. **Database Setup:** Ensure MySQL is running and create a database named `netflix_clone`.
2. **Environment Variables:** Set the following environment variables (or rely on default local values):
   - `DB_URL` (default: `jdbc:mysql://localhost:3306/netflix_clone?...`)
   - `DB_USERNAME` (default: `root`)
   - `DB_PASSWORD` (Your MySQL password)
   - `JWT_SECRET` (A strong secret key for JWT signing)
   - `JWT_EXPIRATION_MS` (default: `86400000`)
   - `REDIS_HOST` (default: `localhost`)
   - `REDIS_PORT` (default: `6379`)
   - `REDIS_PASSWORD` (leave empty for local Docker Redis)
   - `ALLOWED_ORIGIN` (default: `http://localhost:3000`)
3. **Run Redis:**
```bash
   docker run --name netflix-redis -p 6379:6379 -d redis
```
4. **Run Application:**
   Navigate to `netflix-clone` and start the Spring Boot app:
```bash
   cd netflix-clone
   mvn spring-boot:run
```

### Frontend Setup

1. **Environment Variables:** Set the following environment variable (or let it default to localhost):
   - `REACT_APP_API_BASE_URL` (default: `http://localhost:8080`)
2. **Install Dependencies:**
```bash
   cd netflix-clone-frontend
   npm install
```
3. **Run Application:**
```bash
   npm start
```

## API Documentation

- **Local:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **Live:** [https://netflix-clone-1-zr5z.onrender.com/swagger-ui/index.html](https://netflix-clone-1-zr5z.onrender.com/swagger-ui/index.html)