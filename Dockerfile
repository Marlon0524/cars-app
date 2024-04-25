FROM openjdk:17
VOLUME /tmp
EXPOSE 8020
ADD ./target/cars-0.0.1-SNAPSHOT.jar carsApp.jar
ENTRYPOINT ["java", "-jar", "carsApp.jar"]