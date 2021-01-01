FROM java:8-jdk-alpine

WORKDIR /usr/app

COPY jukebox-0.0.1-SNAPSHOT.jar .

RUN ls

WORKDIR /usr/app

ENTRYPOINT ["java","-jar","jukebox-0.0.1-SNAPSHOT.jar"]