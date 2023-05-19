FROM openjdk:17-oracle
RUN addgroup -S textutilapp && adduser -S textutilapp -G textutilapp
USER textutilapp:textutilapp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]