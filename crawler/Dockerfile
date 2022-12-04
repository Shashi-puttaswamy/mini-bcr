FROM openjdk:11

RUN mkdir /crawler/

COPY target/crawler-*.jar /crawler/crawler.jar

WORKDIR /crawler

CMD ["java", "-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:5005,server=y,suspend=n","-jar", "crawler.jar"]