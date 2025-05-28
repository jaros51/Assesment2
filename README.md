# Library Management System (Assignment 2)

A Spring Boot-based library management system that enables the management of books and their copies.

## Project Description

The application is built as a REST API that allows:
- Book management (create, update, delete, retrieve)
- Book copy management
- Various search criteria

## Technologies

The project uses the following technologies:
- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- Spring MVC
- Flyway for database migrations
- MySQL (production DB) / H2 (development DB)
- Lombok
- MapStruct and ModelMapper for object mapping

## Requirements

To run the application you need:
- JDK 21 or higher
- Maven 3.6+
- Docker (for MySQL database)

## Installation and Setup

### 1. Java Installation

Ensure you have Java 21 or higher installed:
```bash
java -version
```
If Java is not installed or version is below 21, download and install from Oracle's website.



### 2. MySQL Setup with Docker


Run MySQL using Docker:
```bash
docker run --name mysql-library -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=zadanieDb -p 3306:3306 -d mysql:latest
```

This command:
Creates a MySQL container named 'mysql-library'
Sets root password to 'secret'
Creates database 'zadanieDb'
Maps port 3306 to host machine

3. Clone Repository

git clone <repository-url>
cd <repository-directory>

4. Run Application
 Build and run using Maven:
```bash
mvn clean install
mvn spring-boot:run
```
The application will be available at http://localhost:8080

Fast web lookup is here: http://localhost:8080/view/books

Pageable book list is here: http://localhost:8080/api/books/pageable?page=2&size=2