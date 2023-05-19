# Requirements

For local development/build - Java 8

# How to build

    gradle build

# Build docker image yourself

    docker build . -t tickoon/text-utils-rest-api

# Download the image from dockerhub and run it

Image name: tickoon/text-utils-rest-api

Url: https://hub.docker.com/repository/docker/tickoon/text-utils-rest-api

To run use the command below.

# Run the docker container

    docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 tickoon/text-utils-rest-api

Swagger/Open API Specs: Once the container is running, use the rest end points by below url - 

    http://localhost:8080/swagger-ui/index.html

