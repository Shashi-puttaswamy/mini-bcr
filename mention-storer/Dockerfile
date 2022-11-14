FROM openjdk:11

RUN mkdir /mentionstorer/

COPY target/mention-storer-*.jar /mentionstorer/mentionstorer.jar

WORKDIR /mentionstorer

CMD ["java", "-jar", "mentionstorer.jar"]