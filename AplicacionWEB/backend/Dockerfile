FROM maven:3-openjdk-11 as builder

WORKDIR /project

COPY asociationPlatform/pom.xml /project/

COPY asociationPlatform/src /project/src

RUN mvn package 


FROM openjdk:11

WORKDIR /usr/src/app/

COPY --from=builder /project/target/*.jar /usr/src/app/

EXPOSE 8443

CMD ["java", "-jar", "asociationPlatform-0.0.1-SNAPSHOT.jar" ]