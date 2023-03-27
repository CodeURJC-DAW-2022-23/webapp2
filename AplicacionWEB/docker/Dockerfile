FROM maven:3-openjdk-17 as builder

WORKDIR /project

COPY backend/asociationPlatform/pom.xml /project/

COPY backend/asociationPlatform/src /project/src

RUN mvn package 


FROM openjdk:17

WORKDIR /usr/src/app/

COPY --from=builder /project/target/*.jar /usr/src/app/

EXPOSE 8443

CMD ["java", "-jar", "asociationPlatformApplication.jar" ]