# Search and pagination with spring
<p align="center">
</p>

---
<li> Welcome to search and pagination, It's a simple api that implements searching and pagination on Students that have different subjects and Addresses</li>

##  📝Table of content

---
- [Built Using](#built).
- [Features](#features).
- [Requirements](#requirements).
- [Installation Steps](#installation).
- [Api documentation](#api).
- [Acknowledgements](#acknowledgements).


## ⛏️ Built Using <a name = "built"></a>

---
- [MySQL](https://www.mongodb.com/) - Database
- [Java](https://docs.oracle.com/en/java/) - Programming Language
- [Spring boot](https://spring.io/projects/spring-boot) - Web Framework

## 🧐Features <a name = "features"></a>

---
- endpoint to find student by name
- endpoint to find students by the city of their address(one-to-one)
- endpoint to find students by the name of their subjects(one-to-many)
- endpoint to find students by any of them or them all combined using spring specification
- endpoint to find students with specification and with pagination 
- 3 endpoints to practice on pagination with spring (page - pageListHolder - QueryMethod)

## 🔧Requirements <a name = "requirements"></a>

---
- java => 17
- spring boot => 3.2.3
- maven
- MySQL

## 🚀 Installation Steps <a name = "installation"></a>

---
First clone this repository, install the dependencies, and setup your .env file.

````
git clone https://github.com/VlixAli/Jwt-with-spring-
mvn clean install
cd src/main/resources
cp .env.example .env
````

then run the following commands to run the project

````
cd ../../../
mvn spring-boot:run
````

the application will be available at [http://localhost:8080](http://localhost:8080)



## ✍️ Api documentation <a name = "api"></a>

---

for how to use it check the documentation [search and pagination with spring](https://documenter.getpostman.com/view/23171948/2sA3Bj9u8t)

## 🎉 Acknowledgements <a name = "acknowledgements"></a>

---

this simple project was created based on this playlist [Spring Boot | Search Specifications | Criteria API](https://www.youtube.com/playlist?list=PLoyb0HJlmv_lvsJv02JKe7hz45oB3qlgX)
 for search and this playlist [Spring Boot | Pagination](https://www.youtube.com/playlist?list=PLoyb0HJlmv_kKDuTCZqBwUrKDXbGLPriw) 
for pagination .
