FROM openjdk:11

RUN mkdir /mentiongenerator/

COPY target/mention-generator-*.jar /mentiongenerator/mentiongenerator.jar

WORKDIR /mentiongenerator

CMD ["java", "-jar", "mentiongenerator.jar"]