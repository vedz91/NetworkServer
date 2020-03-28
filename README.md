# NetworkServer

How to start the NetworkServer application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/NetworkServer-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`


## To Do
- [x] Create a Static Endpoint
- [x] Setup a Config File
- [x] Integrate Swagger
- [x] Configure jUnit
- [x] Integrate Lombok
- [x] Integrate a downward dependency using Retrofit or other
- [x] Create a dynamic endpoint
- [ ] Mock service calls
- [x] Setup CI
- [x] Dockerize App
- [ ] Setup Authentication
  

## Nice To Have
- [ ] Enable PMD
- [ ] Enable Bugzilla
- [ ] Create an annotation
- [ ] Dependency Injection 
- [ ] Mock DI

