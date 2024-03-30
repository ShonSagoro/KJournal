FROM openjdk:17

ADD target/kjournal.jar kjournal.jar

ENTRYPOINT ["java", "-jar","kjournal.jar"]