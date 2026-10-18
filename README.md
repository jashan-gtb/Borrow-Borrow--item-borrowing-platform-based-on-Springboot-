# Borrow Borrow

A peer-to-peer item borrowing platform built with Spring Boot that enables users to lend and borrow items within a community.

## Features

- **User Management**: Create, update, and manage user profiles
- **Item Catalog**: Users can list items available for borrowing with descriptions and images
- **Borrow Requests**: Send and track borrow requests with status management (PENDING, RECEIVED, APPROVED, REJECTED)
- **RESTful API**: Clean and intuitive API endpoints for all operations

## Tech Stack

- **Java 25**
- **Spring Boot 4.1.1**
- **Spring Data JPA** - Database ORM
- **PostgreSQL** - Relational database
- **Lombok** - Reduce boilerplate code
- **Maven** - Build and dependency management

## Prerequisites

- Java 25 or higher
- Maven 3.6+
- PostgreSQL 12+

## Database Setup

1. Create a PostgreSQL database named `borrow`:
```sql
CREATE DATABASE borrow;
```

2. Update database credentials in `src/main/resources/application.yaml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/borrow
    username: your_username
    password: your_password
```

## Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/borrow-borrow.git
cd borrow-borrow
```

2. Build the project:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Users

- `GET /api/v1/users/view` - Get all users
- `GET /api/v1/users/view/{id}` - Get user by ID
- `POST /api/v1/users/post` - Create a new user
- `PUT /api/v1/users/update/{id}` - Update user
- `DELETE /api/v1/users/delete/{id}` - Delete user
- `POST /api/v1/users/post-item/{id}` - Add item for a user

### Items

- `GET /api/v1/items/view` - Get all items
- `GET /api/v1/items/view/{id}` - Get item by ID
- `POST /api/v1/items/post` - Create a new item
- `PUT /api/v1/items/update/{id}` - Update item
- `DELETE /api/v1/items/delete/{id}` - Delete item

### Borrow Requests

- `POST /api/v1/borrow-requests/send/{userId}/{itemId}` - Send a borrow request

## Data Models

### Users
- `user_id`: Unique identifier
- `user_name`: User's full name
- `user_email`: User's email address
- `user_password`: User's password
- `sendRequestStatus`: Status of sent requests
- `receiveRequestStatus`: Status of received requests

### Items
- `item_id`: Unique identifier
- `item_name`: Name of the item
- `item_description`: Description of the item
- `item_image`: URL to item image
- `user`: Owner of the item

### Borrow Requests
- `request_id`: Unique identifier
- `request_time`: Timestamp of request
- `requestStatus`: Request status (NULL, PENDING, RECEIVED, APPROVED, REJECTED)
- `user`: Requester
- `items`: Item being requested

## Request Status Flow

1. **NULL** - Initial state
2. **PENDING** - Request sent, awaiting response
3. **RECEIVED** - Request received by item owner
4. **APPROVED** - Request approved by owner
5. **REJECTED** - Request rejected by owner

## Running Tests

```bash
./mvnw test
```

## Project Structure

```
src/main/java/com/jashan/borrow_borrow/
├── controllers/       # REST API controllers
├── dto/              # Data Transfer Objects
├── enums/            # Enumerations
├── models/           # JPA entity models
├── repository/       # JPA repositories
├── service/          # Business logic services
└── BorrowBorrowApplication.java
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License.
