### Stack technique
 - Java 11
 - Spring Boot
 - Spring Data
 - REST-API
 - H2 Database
 - Lombok
Pour lancer l'application en local, vous avez besoin d'installer :

- [JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org)
- [Lombok](https://projectlombok.org/)

### Build & Run 

```
  mvn clean install && mvn spring-boot:run
```
  
### Port
```
  http://localhost:8088
```

### Swagger

Le projet contient les swagger en utilisant openapi-ui. Dans le pom.xml du module backend-rest, vous trouverez la dépendance suivante :
```xml
<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-ui</artifactId>
	<version>1.5.8</version>
</dependency>
```

Dans le fichier application.properties, vous pouvez modifier l'url d'accès au swagger :
```
springdoc.swagger-ui.path=/swagger-ui.html
```

Vous pouvez trier les API exposés avec l'attribut :
```
springdoc.swagger-ui.operationsSorter=method