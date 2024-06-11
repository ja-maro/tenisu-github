# Tenisu
This is a Java Spring Boot demo project about tennis players.  

## Locally
Run tests with  
```mvn clean test```  

Run with   
```./mvnw spring-boot:run```  

Build with
```mvn -B -DskipTests package --file pom.xml```

Package with
```mvn -B package --file pom.xml```

Access on port 8090 : http://localhost:8090/

Endpoints :
- [/players](http://localhost:8090/players)  
- [/players/{id}](http://localhost:8090/players/17)  
- [/statistics](http://localhost:8090/statistics)  


## CI/CD
Use https://learn.microsoft.com/en-us/azure/spring-apps/enterprise/how-to-github-actions?pivots=programming-language-java to deploy this project on Azure Spring Apps

