FROM openjdk:11

RUN mkdir /queryapi/

COPY target/query-api-*.jar /queryapi/queryapi.jar

WORKDIR /queryapi

CMD ["java", "-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:5007,server=y,suspend=n","-jar", "queryapi.jar"]