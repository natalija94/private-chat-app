Backend service:

This is a Spring boot application. 

Communication(receiving new messages) is through WebSocket. The reason why this is a good approach is: exchanging events with high frequency and with low latency.

Application has RestAPI, too. It is documented   and it can be checked using swagger-api:  http://localhost:10700/swagger-ui/index.html. 

Application is covered with JUnit tests. This is also a good approach while I found few bugs while I was writing tests.

(Logic send-message from client  is done through (Rest) HTTP. There is only one reason why I implemented this as Rest instead of  websocket.
I wanted to show that I’m familiar with handling  http requests on the frontend.:) Anyway while receiving messages via websocket, why not send message?:) )

I’ve chosen Hibernate (over jdbc template, i.e.) while it gives us possibility to think less about the DB layer, and to be closer to objects. More or less, we use annotations and appropriate configuration and Hibernate communicates this way directly with DB. A It gives us possibility to avoid queries in most cases (create, update, delete, insert into etc..).
Regarding the DB: I’ve chosen MySql while I assumed It would not require a lot o time for setup.


______________________________________________________________

Chat application is made of two projects: backend and frontend service. Backend and frontend services both have own docker-compose.yml and Dockerfile.
Please start backend service before frontend service.

Backend project (service):
Please checkout project from git: Github https://github.com/natalija94/private-chat-app . Please find two files docker-compose.yml + Dockerfile. 

*Remark:
docker-compose up  :   requires jar file of the backend service =>
Hopefully you have a maven so you can perform the command: clean install. 
After clean install target folder is generated and .jar which we docker compose requires.Please navigate to the folder where jar is generated and perform: docker-compose up. Please run the command docker-compose up - in order to start the service.

(I might be the case that you see some errors in log while deploying. Please be calm while services (MySql+ backend) are not not both successfully started. :)  
It is happening sometimes while MySql service is still not started and spring boot tries to communicate with this service. So, Spring boot app will restart till MySql DB is not running.This isn’t great, but this is  my first docker compose ever, so for me at the moment is success that I made services running and that these communicate with each other:) 
P.S.: The last I started services , I had no errors in log.)
