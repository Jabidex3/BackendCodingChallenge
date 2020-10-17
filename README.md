# BackendCodingChallenge
Developed By: Jabid Methun - jabidmethun@gmail.com

### The Challenge:

Using Spring Boot or Go, and your database of choice (PostgreSQL, MySQL, MongoDB -- any you'd like), develop a microservice for tracking the status of enrollees in a health care program.
- Enrollees must have an id, name, and activation status (true or false), and a birth date
- Enrollees may have a phone number (although they do not have to supply this)
- Enrollees may have zero or more dependents
- Each of an enrollee's dependents must have an id, name, and birth date

The application we will be building will need to be able to do these things:
- Add a new enrollee
- Modify an existing enrollee
- Remove an enrollee entirely
- Add dependents to an enrollee
- Remove dependents from an enrollee
- Modify existing dependents
<br>
====================================================================================
<br>

### Getting Started:
This application was created using Eclipse IDE for Java EE Developers and MySql Workbench. In order to run this application you should follow the steps listed below:
- Clone repository into a folder of your choice
- Import the project into Eclipse IDE
- Then set up a new connection in MySql Workbench on port 3306
  - Based on your username and password for the connection, update those fields in the applications.properties file
- Create a database in MySql Workbench using the following command:
  ``` sql
  create database codingchallengedb;
  ```
- Run CenteneCodingChallengeApplication.java as a Spring boot App or Java Application
