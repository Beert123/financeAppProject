# Financial Tracker API

## Description
A Spring Boot backend API for tracking personal finances such as expenses and earnings.  
It supports creating, viewing, updating, and deleting financial transactions, each associated with a category.
It can also view a summary which provides some additional information about the transactions

## Features
- Add, view, update, and delete transactions
- Categorize each transaction (e.g., Food, Rent, Salary)
- View financial summary (income, expenses, balance)
- H2 in-memory database with web console
- Simple frontend integration

## Prerequisites

- Java 17+
- Gradle or IntelliJ (for running Spring Boot)

## Technologies

- Backend: Java 17, Spring Boot
- Database: H2 (in-memory, with H2 console enabled)
- Testing: JUnit 5 with Mockito
- Frontend: Basic HTML, CSS, JavaScript

## Assumptions:
Default currency is DKK
Categories are predefined in the system - Must be made first

### Access the API
- Backend API: [http://localhost:8080](http://localhost:8080)
- Frontend view: [http://localhost:8080/index.html](http://localhost:8080/index.html)
- H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
  - **JDBC URL**: `jdbc:h2:mem:testdb`  
  - **Username**: `sa`  
  - **Password**: *(leave empty)*
- requests.http: Used to "test" endpoints




