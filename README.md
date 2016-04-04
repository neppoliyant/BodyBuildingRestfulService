# BodyBuildingRestfulService
Backend Restful service for Web and Mobile apps. Simple backend service for getting user, trainer, trainee and workout details.

## Tech Stack
* Spring 
* Couchbase and SQL
* Tomcat(Embedded)

## Pre-requisites
* maven - 3.x
* JDK - 1.8

## How to run the application

#### Build and Run tests
	mvn clean install

### Code coverage
	mvn clean cobertura:cobertura
Reports available in target/site/cobertura/index.html

### DB Views
DB Views are available here 
```
Trainers View
function (doc, meta) {
  if (doc.type == "Trainer") {
  	emit(meta.id, doc);
  }
}

Trainees View
function (doc, meta) {
  if (doc.type == "Trainee") {
  	emit(meta.id, doc);
  }
}

```
### APIs:

![Alt text](/SwaggerApi.png?raw=true "Swagger")


