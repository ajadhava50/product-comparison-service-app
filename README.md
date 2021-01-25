
# Comparison Service Application
Comparison service used to compare product data

## Local Development
Prerequisites:
- Install JDK8
- Build application with maven: mvn clean install 
- Run Integration test mvn clean install -DskipTests=false set correct path before executing 
- update the application.properties for "productData.folder" path (batch import path of json files i.e data/products.json)
- Run with: java -jar product-comparison-service-app-${version}.jar
- OR Build and Run from IntelliJ IDEA

## Application URl's
- Swagger
* [http://localhost:8888/swagger-ui/](http://localhost:8888/swagger-ui/)

## Local DB
- h2 db
  http://localhost:8888/h2-console