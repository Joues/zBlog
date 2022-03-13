FROM java:8

VOLUME /ROOT

ADD zblog-0.0.1-SNAPSHOT.jar zblog.jar

RUN bash -c 'touch /zblog.jar'

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "zblog.jar"]