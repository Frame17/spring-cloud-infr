# Spring Cloud Infr

This is a sample project using Spring Boot and Spring Cloud meant to represent
 a part of microservices infrastructure. 
 
There is a docker-compose file to get everything up and running. Simply use the command
`docker-compose up -d --scale processor=2` and you'll get all containers and services 
you need ready. For now, however, you are gonna need to change the absolute path of
`SPRING_CLOUD_CONFIG_SERVER_GIT_URI`, but this inconvenience is temporary. 

### Infrastructure

Currently there are 2 major blocks: the gateway and the processors, that are have a 
`one-to-many` relationship. Every time the gateway receives a request on `/demo` endpoint
it dynamically resolves the available processors uri and makes a request to either one
of them.