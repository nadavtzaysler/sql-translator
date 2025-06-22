# HQL2SQL

HQL2SQL is a Spring Boot application designed to translate SQL queries written in different dialects (such as Hive or Oracle) into Trino-compatible SQL. It leverages Apache Calcite for parsing and translation, providing a REST API for easy integration.

## Features
- Translate SQL queries from Hive or Oracle syntax to Trino SQL
- REST API endpoints for translation and listing supported syntaxes
- Built with Spring Boot and Apache Calcite

## API Endpoints

### List Supported Syntaxes
- **GET** `/api/supported`
- **Response:**
  ```json
  ["Hive", "Oracle"]
  ```

### Translate Query
- **POST** `/translate`
- **Request Body:**
  ```json
  { "query": "SELECT * FROM my_table" }
  ```
- **Request Parameters:**
  - `input`: The input SQL dialect (e.g., Hive, Oracle)
  - `output`: The output SQL dialect (e.g., Trino)
- **Response:**
  ```json
  { "trinoSql": "SELECT * FROM my_table" }
  ```

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Build and Run (Maven)
1. Navigate to the `calcite` directory:
   ```sh
   cd HQL2SQL/calcite
   ```
2. Build the project:
   ```sh
   mvn clean package -DskipTests
   ```
3. Run the application:
   ```sh
   java -jar target/*.jar
   ```
4. The API will be available at [http://localhost:8082](http://localhost:8082)

### Build and Run (Docker)
1. From the project root, build the Docker image:
   ```sh
   docker build -t hql2sql ./calcite
   ```
2. Run the Docker container:
   ```sh
   docker run -p 8082:8082 hql2sql
   ```
3. The API will be available at [http://localhost:8082](http://localhost:8082)

## Configuration
- The server runs on port `8082` by default (see `application.properties`).

## Example Usage
```
curl -X POST "http://localhost:8082/api/translate?input=Hive&output=Trino" \
  -H "Content-Type: application/json" \
  -d '{"query": "SELECT * FROM my_table"}'
```

## License
we have no license

