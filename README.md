# TCP Chat Server

A simple multi-client TCP chat server built using **Java Sockets** inside a **Spring Boot** application.  
It allows multiple users to connect, log in, send messages, and receive real-time chat updates.

---

## What to Deliver

### 1. Source Code
Implementation of the chat server using **Java Sockets** inside a **Spring Boot** application.  
Uses only standard Java libraries (`java.net`, `java.io`, `java.util.concurrent`) — no external chat frameworks.

### 2. README File
Contains:
- Steps to run the server
- Step 2-Build the project
mvn clean install

#### Run the server
mvn spring-boot:run

#### Expected output
[+] Chat Server started on port 4000



## Example commands to connect (via `nc` or `telnet`)
### Using netcat
nc localhost 4000

### OR using telnet
telnet localhost 4000
## Example chat interaction between multiple users
### client 1
$ nc localhost 4000

LOGIN akshay

OK

INFO akshay joined the chat

MSG Hi everyone

INFO gagan joined the chat

MSG gagan yo

INFO disconnected due to inactivity

### client 1
$ nc localhost 4000

LOGIN akshay

OK

INFO akshay joined the chat

MSG Hi everyone

INFO gagan joined the chat

MSG gagan yo

INFO disconnected due to inactivity

---

### 3. Screen Recording (Compulsory)
A short demo video (1–2 minutes) showing:
- The server running
- Two clients chatting in real time  

**Video Link:** [https://www.loom.com/share/2f8df38efe5e44e7b9172f723f0aa373](https://www.loom.com/share/2f8df38efe5e44e7b9172f723f0aa373)

### 4. Deployment Link
If not hosted, the video demonstrates the server running locally.

---

## How to Run

### Requirements
- Java 17 or later  
- Maven 3.6+  
- Spring Boot 3.x  

---
