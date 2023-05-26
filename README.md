# User Service
## DATABASE
H2 database is used for this application, it will be automatically created when the application runs for the first time. username = root and password = root, you can change according to your system credentials.
Database can be viewed at http://localhost:8080/h2-console/
## Application Properties
spring.datasource.initialization-mode=always change this to spring.datasource.initialization-mode=never in application.properties after first time run.
## Application Details
default applications port is used (8080), so the base url will be http://localhost:8080/
Swagger is added for the ease of end points documentation at http://localhost:8080/swagger-ui/
All the Request and Response bodies are documented via Swagger.