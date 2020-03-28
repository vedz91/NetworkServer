FROM openjdk:8u121-jre-alpine
MAINTAINER Vedang Sharma

WORKDIR /var/network-api

ADD target/NetworkServer-1.0-SNAPSHOT.jar /var/network-api/NetworkServer-1.0-SNAPSHOT.jar
ADD config-docker.yml /var/network-api/config.yml

EXPOSE 9090 9091

ENTRYPOINT ["java", "-jar", "NetworkServer-1.0-SNAPSHOT.jar", "server", "config.yml"]