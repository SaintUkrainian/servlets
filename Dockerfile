FROM maven:openjdk

WORKDIR /servlets-app

COPY . .

RUN mvn compile

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]