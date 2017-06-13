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
Result is an average time of a HTTP request processing executed 2000 times in a @Test.
 
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

 #### plain methods invocation with 12 org.springframework.validation.Validator in an ApplicationContext
 ##### SomeDTO
* 1121587.42 ns - valid
* 1279418.15 ns - invalid on javax validator
* 1285886.55 ns ns - invalid on a custom validator
   
 ##### AnotherDTO
* 1247554.52 ns - valid
* 1175909.58 ns - invalid on javax validator
* 1298877.68 ns - invalid on a custom validator

  #### custom init binder with 12 org.springframework.validation.Validator in an ApplicationContext
  ##### SomeDTO
* 1342343.95 ns - valid
* 1464712.75 ns - invalid on javax validator
* 1436413.91 ns - invalid on a custom validator
   
  ##### AnotherDTO
* 1202006.61 ns - valid
* 1542071.70 ns - invalid on javax validator
* 1374212.49 ns - invalid on a custom validator

  #### custom init binder with 22 org.springframework.validation.Validator in an ApplicationContext
  ##### SomeDTO
* 1336569.17 ns - valid
* 1422664.00 ns - invalid on javax validator
* 1519208.23 ns- invalid on a custom validator
   
  ##### AnotherDTO
* 1199839.20 ns - valid
* 1433293.67 ns - invalid on javax validator
* 1324509.38 ns - invalid on a custom validator


Seems like results getting better with each test run, so last one 
measures may show better results because of this.
  
  
## Summary
  Custom init binder allows to avoid code duplication in controllers
  and adds acceptable overhead for non latency critical applications
  

  
   
 
 
 


  