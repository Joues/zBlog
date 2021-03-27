FROM java:8
ADD target/*.jar zblog-0.0.1-SNAPSHOT.jar
EXPOSE 8086
RUN echo "Asia/Shanghai" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata
ENTRYPOINT ["java","-jar","/zblog-0.0.1-SNAPSHOT.jar"]