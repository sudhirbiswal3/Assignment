# bookmgmt
Below details in this file shows how to deploy SpringBoot RESTful web service application with Docker

# Prerequisite
Installed:

Docker

Git

Optional:
Docker-Compose

Java 11

Maven 3.x

H2

# Steps

# Run and Build as standlone application

Clone the project from the git as mentioned and run below command

1 : mvn spring-boot:run

2: verify using :  curl localhost:8090/api/bookmgmt/v1/get/1


# Build Docker image

Clone source code from git

$  git clone https://github.com/sudhirbiswal3/bookmgmt.git .

$ docker build -t="bookmgmt" .

Maven build will be executes during creation of the docker image.

Note:if you run this command for first time it will take some time in order to download base image from DockerHub

# Run Docker Container

$ docker run -p 8090:8090 -it --rm bookmgmt

# Test application

$ curl localhost:8090/api/bookmgmt/v1/get/1

the respone should be:

{"isSuccess":false,"bookResponse":[{"errorMessage":"No record found for given value: "}]}-

# Stop Docker Container:

docker container ls

docker stop imageid

# Run with docker-compose

Build and start the container by running

$ docker-compose up -d

# Test application with curl command

$ curl localhost:8090/api/bookmgmt/v1/get/1

the respone should be:

{"isSuccess":false,"bookResponse":[{"errorMessage":"No record found for given value: "}]}

# Stop Docker Container:

docker-compose down
