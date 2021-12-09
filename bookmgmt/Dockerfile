# Maven build container 

FROM maven:3.6.3-openjdk-8 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk

#maintainer 
MAINTAINER bsudhir.biswal3@gmail.com
#expose port 8090
EXPOSE 8090

#default command
CMD java -jar /data/bookmgmt-0.1.0.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/target/bookmgmt-0.1.0.jar /data/bookmgmt-0.1.0.jar
