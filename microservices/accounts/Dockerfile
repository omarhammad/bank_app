FROM openjdk:22-jdk-slim
MAINTAINER omarhammad_97
COPY build/libs/accounts-0.0.1-SNAPSHOT.jar accounts.jar
ENTRYPOINT ["java","-jar","accounts.jar","--spring.profiles.active=prod"]