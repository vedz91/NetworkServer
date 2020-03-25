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
- [ ] Create a Static Endpoint
- [ ] Setup a Config File
- [ ] Environment based Configuration
- [ ] Integrate Swagger
- [ ] Configure jUnit
- [ ] Dependency Injection
- [ ] Mock DI
- [ ] Setup CI
- [ ] Setup Authentication
- [ ] Integrate a downward dependency using Retrofit or other
- [ ] Create a dynamic endpoint
- [ ] Mock service calls  

## Nice To Have
- [ ] Enable PMD
- [ ] Enable Bugzilla
- [ ] Setup PSQL via docker
- [ ] Create an annotation 


