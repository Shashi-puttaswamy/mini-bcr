FROM openjdk:11

RUN mkdir /crawler/

COPY target/crawler-*.jar /crawler/crawler.jar

WORKDIR /crawler

CMD ["java", "-jar", "crawler.jar"]