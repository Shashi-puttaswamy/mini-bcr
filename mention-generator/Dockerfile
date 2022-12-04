FROM openjdk:11

RUN mkdir /mentiongenerator/

COPY target/mention-generator-*.jar /mentiongenerator/mentiongenerator.jar

WORKDIR /mentiongenerator

CMD ["java", "-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:5006,server=y,suspend=n","-jar", "mentiongenerator.jar"]