FROM eclipse-temurin:17-jdk-jammy
RUN addgroup textutilsapigroup; adduser  --ingroup textutilsapigroup --disabled-password svctextutilsapi
USER svctextutilsapi
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]