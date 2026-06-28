# 🗳️ Votezy - Online Voting System

## 📌 Overview

Votezy is a backend-based Online Voting System developed using **Java, Spring Boot, Spring Data JPA, Hibernate, and PostgreSQL**. The application provides secure REST APIs for voter registration, candidate management, voting, and election result declaration.

---

## ✨ Features

- Voter Registration & Management
- Candidate Management
- Secure Vote Casting
- One Vote Per Voter
- Election Result Declaration
- RESTful APIs
- Bean Validation
- Exception Handling
- Layered Architecture (Controller → Service → Repository)

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Postman

---

## 📂 Project Structure

```
src
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
└── resources
```

---

## ⚙️ Installation

### Clone Repository

```bash
git clone https://github.com/Ojas991/Votezy-Online-Voting-System-.git
```

### Configure Database

Update `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/votezy
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

### Run

Run

```
VotezyApplication.java
```

Application starts at:

```
http://localhost:8080
```

---

## 📚 API Modules

### Voter
- Register Voter
- Get Voter
- Update Voter
- Delete Voter

### Candidate
- Add Candidate
- Update Candidate
- Delete Candidate
- View Candidates

### Voting
- Cast Vote
- Prevent Duplicate Voting

### Election Result
- Declare Result
- View Result

---

## 🗄️ Database

Entities used:

- Voter
- Candidate
- Vote
- ElectionResult

---

## 📮 API Testing

All REST APIs were tested using **Postman**.

---

## 🚀 Future Improvements

- Spring Security
- JWT Authentication
- Swagger Documentation
- React Frontend
- Docker Deployment

---

## 👨‍💻 Author

**Ojesh Bisen**

B.Tech – Computer Science & Engineering

GitHub: https://github.com/Ojas991

---

⭐ If you like this project, consider giving it a Star.
