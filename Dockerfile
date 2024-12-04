
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/feedback-query-0.0.1-SNAPSHOT.jar feedback-query-service.jar

EXPOSE 9100

ENTRYPOINT ["java", "-jar", "feedback-query-service.jar"]

ENV TZ=Asia/Seoul