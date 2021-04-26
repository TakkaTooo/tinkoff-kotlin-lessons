#base image
FROM openjdk:8-jdk

#env
ENV LOG_LEVEL INFO

ADD build/libs/trip-rest-1.0.jar /usr/src/trip-rest-1.0.jar

WORKDIR /usr/src

EXPOSE 8080
ENTRYPOINT java -Dlogging.level.ru.docker=$LOG_LEVEL -Ddb.url=$JDBC_URL -Ddb.user=$JDBC_USER -Ddb.password=$JDBC_PASSWORD -DcarRest.url=$CAR_REST_URL -jar trip-rest-1.0.jar
