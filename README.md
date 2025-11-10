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
- Example commands to connect (via `nc` or `telnet`)  
- Example chat interaction between multiple users  

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

### Step 1 — Clone the Repository
```bash
git clone https://github.com/akshayv06/tcp-chatserver.git
cd tcp-chatserver

### Step 2 — Build the Project
```bash
mvn clean install
