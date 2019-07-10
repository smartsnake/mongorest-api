FROM java:8
VOLUME /tmp
ADD target/mongorest-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# The application's jar file
ARG JAR_FILE=target/mongorest-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/test", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
