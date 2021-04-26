FROM openjdk:8-jdk

ENV LOG_LEVEL INFO

ADD build/libs/shadow-1.0-all.jar /usr/src/shadow-1.0-all.jar

WORKDIR /usr/src

EXPOSE 8081

ENTRYPOINT java -Ddb.url=$JDBC_URL -Ddb.user=$JDBC_USER -Ddb.password=$JDBC_PASSWORD -jar shadow-1.0-all.jar