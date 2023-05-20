# Requirements

For local development/build - Java 17

# How to build
```shell
gradle clean bootJar
```

# Build docker image yourself

```shell
docker build . -t tickoon/text-utils-rest-api
```

# Download the image from dockerhub and run it

Image name: tickoon/text-utils-rest-api:1.0.0

Url: https://hub.docker.com/r/tickoon/text-utils-rest-api/tags

To run use the command below.

# Run the docker container

```shell
docker run -p 8080:8080 tickoon/text-utils-rest-api:1.0.0
```

Swagger/Open API Specs: Once the container is running, use the rest end points by below url - 

    http://localhost:8080/swagger-ui/index.html


