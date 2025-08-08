FROM openjdk:21
LABEL authors="kevinliu"

# RUN addgroup -S formfio && adduser -S formfio -G formfio

WORKDIR /usr/app
COPY build/libs/formfio-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]