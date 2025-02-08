FROM openjdk:22-jdk-slim
MAINTAINER omarhammad_97
COPY build/libs/loans-0.0.1-SNAPSHOT.jar loans.jar
ENTRYPOINT ["java","-jar","loans.jar","--spring.profiles.active=prod"]