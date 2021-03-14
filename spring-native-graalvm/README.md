# Spring Native GraalVM

This tutorial is for demonstrating the usage of Spring Native for compiling Spring Boot applications
to native executables using GraalVM native-image compiler.

Check the [official documentation](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/)
for more information about the project.

## How to run it

Build the Spring Boot application as a native GraalVM image:

```bash
./gradlew bootBuildImage
```

Run the native image:

```bash
docker-compose up -d
```

Call the application REST endpoint:

```bash
http :8080
```