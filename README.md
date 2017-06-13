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
 
 
 ### Benchmarks 
 
 Benchmarks has been performed in Junit tests and results may be imprecise.

 
 
 #### plain methods invocation
 ##### SomeDTO
 * 990633.72 ns - valid
 * 1201762.22 ns - invalid on javax validator
 * 1269818.67 ns - invalid on a custom validator
   
 ##### AnotherDTO
 * 947419.52 ns - valid
 * 1175909.58 ns - invalid on javax validator
 * 1095785.90 ns - invalid on a custom validator
 
 #### custom init binder
 ##### SomeDTO
 * 1314443.03 ns - valid
 * 1225969.91 ns - invalid on javax validator
 * 1271690.38 ns - invalid on a custom validator
 
 ##### AnotherDTO
  * 1087886.78 ns - valid
  * 1301572.77 ns - invalid on javax validator
  * 1175526.55 ns - invalid on a custom validator
  
   
 
 
 


  