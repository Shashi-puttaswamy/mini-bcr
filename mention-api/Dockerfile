FROM openjdk:11

RUN mkdir /mentionapi/

COPY target/mention-api-*.jar /mentionapi/mentionapi.jar

WORKDIR /mentionapi

CMD ["java", "-jar", "mentionapi.jar"]