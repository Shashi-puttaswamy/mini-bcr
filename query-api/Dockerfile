FROM openjdk:11

RUN mkdir /queryapi/

COPY target/query-api-*.jar /queryapi/queryapi.jar

WORKDIR /queryapi

CMD ["java", "-jar", "queryapi.jar"]