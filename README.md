TCP Chat Server

A simple multi-client TCP chat server built using Python sockets (standard library only).
It allows multiple users to connect, log in, send messages, and receive real-time chat updates.

What to Deliver
1️. Source Code

Implementation of the chat server using Java Sockets inside a Spring Boot application.

Uses only standard Java libraries (java.net, java.io, concurrent) — no external chat frameworks.
2️. README File

Contains:

Steps to run the server

Example commands to connect (via nc or telnet)

Example chat interaction between multiple users

3️.  Screen Recording (Compulsory)

Watch a short demo video (1–2 minutes) showing:

The server running

Two clients chatting in real time

Video Link: https://www.loom.com/share/2f8df38efe5e44e7b9172f723f0aa373

4️. Deployment Link
If not hosted, the video demonstrates the server running locally.

 How to Run
 Requirements
Java 17 or later

Maven 3.6+

Spring Boot 3.x
Build and Run the Server
Step 1 — Clone the Repository
git clone https://github.com/akshayv06/tcp-chatserver.git
cd tcp-chatserver

Step 2 — Build the Project
mvn clean install

Step 3 — Run the Server
mvn spring-boot:run

The server starts listening on:

localhost:4000

💬 Connect Clients

Use netcat (nc) or telnet to connect from multiple terminals.

nc localhost 4000

Example Chat Session

Client 1:

$ nc localhost 4000
LOGIN akshay
OK
INFO akshay joined the chat
MSG Hi everyone
INFO gagan joined the chat
MSG gagan yo
INFO disconnected due to inactivity

Client 2:

$ nc localhost 4000
LOGIN gagan
OK
INFO gagan joined the chat
MSG yo
INFO akshay disconnected

When a user disconnects:

INFO akshay disconnected

🕒 Idle Timeout / Heartbeat 

The server disconnects inactive users automatically after 60 seconds of inactivity and notifies all others.

👨‍💻 Author

Akshay Verma
📧 Email: av2139456@gmail.com

🔗 GitHub: https://github.com/akshayv06
