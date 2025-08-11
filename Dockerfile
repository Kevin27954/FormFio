FROM gradle:jdk21-ubi-minimal AS base
LABEL authors="kevinliu"

WORKDIR /usr/app
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle dependencies

COPY src/ src/

FROM base AS dev
EXPOSE 8080
CMD ["gradle", "bootRun", "--no-daemon"]

FROM base AS build
RUN gradle build -x test

FROM eclipse-temurin:21-jre-alpine AS production
WORKDIR /usr/app
COPY --from=build /temp/build/libs/formfio-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]