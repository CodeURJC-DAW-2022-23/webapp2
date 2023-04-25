
FROM node:16-alpine AS builderNode

WORKDIR /project

COPY frontend/ .

RUN npm install
RUN npm run ng build -- --configuration production --base-href=/new/

#---------------------------------------------------

FROM maven:3-openjdk-11 as builder

WORKDIR /project

COPY backend/asociationPlatform/pom.xml /project/

COPY backend/asociationPlatform/src /project/src

COPY --from=builderNode /project/dist/frontend /project/src/main/resources/static/new

RUN mvn package 

#---------------------------------------------------

FROM openjdk:11

ENV JAVA_TOOL_OPTIONS="-Xss256K -XX:ReservedCodeCacheSize=64M -XX:MaxMetaspaceSize=100000K -Xmx64M"

WORKDIR /usr/src/app/

COPY --from=builder /project/target/*.jar /usr/src/app/

EXPOSE 8443

CMD ["java", "-jar", "asociationPlatform-0.0.1-SNAPSHOT.jar" ]