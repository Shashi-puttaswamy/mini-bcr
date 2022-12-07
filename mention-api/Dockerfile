FROM openjdk:11

RUN mkdir /mentionapi/

COPY target/mention-api-*.jar /mentionapi/mentionapi.jar

WORKDIR /mentionapi

CMD ["java", "-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:5009,server=y,suspend=n","-jar", "mentionapi.jar"]