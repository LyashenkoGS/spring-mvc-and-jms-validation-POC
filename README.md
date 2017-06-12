# spring-mvc-and-jms-validation-POC

POC of implementing validation in Spring framework based web applications

## Requirements

* Java 1.8

## Build and run

    ./mvnw install spring-boot:run

## Test  

#### Positive cases

POST localhost:8080/somedto

{"id":1,"name":"name","numberOfSomething":20,"pricePer1KgOfRaspberry":10}


POST localhost:8080/anotherdto

{"id":1,"amount":10,"someMagicNumber":20}
  
#### Negative cases

Request below produce invoke validation exception

POST localhost:8080/somedto

{"id":1,"name":"name","numberOfSomething":21,"pricePer1KgOfRaspberry":10}


POST localhost:8080/anotherdto

{"id":1,"amount":10,"someMagicNumber":21}
 


  