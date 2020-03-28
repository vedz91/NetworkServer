FROM openjdk:8u121-jre-alpine
MAINTAINER Vedang Sharma

WORKDIR /var/network-api

ADD target/NetworkServer-1.0-SNAPSHOT.jar /var/network-api/NetworkServer-1.0-SNAPSHOT.jar
ADD config.yml /var/network-api/config.yml

EXPOSE 9000 9001

ENTRYPOINT ["java", "-jar", "NetworkServer-1.0-SNAPSHOT.jar", "server", "config.yml"]