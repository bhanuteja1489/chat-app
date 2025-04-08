# Personal Chat App

A real-time chat application built with **Spring Boot**, **MongoDB**, and **WebSockets (STOMP)**.

---

## Features

-  User registration & login
-  One-to-one and group chats
-  Real-time messaging via WebSockets

---

## Tech Stack

- **Backend**: Spring Boot (WebSocket, STOMP, REST APIs)
- **Database**: MongoDB
- **Frontend**: HTML, ReactJS, Bootstrap 
- **Messaging Protocol**: STOMP over WebSocket

---



---

## Setup Instructions

### 1. Clone the repo
```bash
git clone https://github.com/bhanuteja1489/chat-app.git
cd chat-app
```
### 2. Start MongoDB
```bash
docker-compose up
```
### 3. Run spring-boot app
```bash
./mvnw spring-boot:run
```

## Todo\'s
* Add JWT Authentication
* Support for media(audio/video/file) 
* Typing indicator
* File preview support
* Audio & video call support
* Update to RabbitMQ/ActiveMQ for better performance

