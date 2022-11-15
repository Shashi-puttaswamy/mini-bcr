FROM openjdk:11

RUN mkdir /mentionstorer/

COPY target/mention-storer-*.jar /mentionstorer/mentionstorer.jar

WORKDIR /mentionstorer

CMD ["java", "-agentlib:jdwp=transport=dt_socket,address=0.0.0.0:5008,server=y,suspend=n","-jar", "mentionstorer.jar"]