FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY . /app
RUN apk add maven
RUN mvn install
ENTRYPOINT ["java","-jar","target/sismepe-spring.jar"]