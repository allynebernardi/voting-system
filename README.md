## Voting System using Spring Webflux and MongoDB

   * [About](#About)
   * [Requirements](#Requirements)
   * [Installation](#Installation)
   * [Usage](#Usage)
   * [APIs](#APIs)
   * [Technologies](#Technologies)


###### About
 This API Provides some endpoints for a voting system in a company, where each member has one vote, and decisions are taken in assemblies, by vote.

###### Requirements
 - Java 11
 - Gradle 6.6
 
###### Installation
  ```bash
  git clone https://github.com/allynebernardi/voting-system.git
  ```
  
###### Usage
   ```bash
   $ gradle bootRun
   ```
The server will start at <http://localhost:8080>.

###### Exploring the Rest APIs

The application defines following REST APIs

```
Agendas (Pautas) API:
1. GET /agendas - Get All Agendas

2. POST /agendas - Create a new Agenda

3. GET /agendas/{id} - Retrieve a Agenda by Id

3. PUT /agendas/{id} - Update a Agenda

4. DELETE /agendas/{id} - Delete a Agenda
------------------------------------------------------
Associates API:
1. GET /Associates - Get All Associates

2. PUT /Associates - Update a  Associate

3. GET /Associates/{id} - Retrieve a Associate by Id

------------------------------------------------------
Session API:
1. GET /sessions/result/{agendaId} - Get Votting Results by Agenda

2. POST /sessions -Creating new Session and register Vote
 ```


###### 🛠 Technologies 
The following tools were used in the construction of the project:

- [Gradle](https://docs.gradle.org)
- [Spring Webflux](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/web-reactive.html)
- [MongoDB](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-mongodb)
- [Swagger](https://swagger.io/)
