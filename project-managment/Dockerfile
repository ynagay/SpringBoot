# Use an existing docker image as a base
FROM ubuntu-jdk

MAINTAINER Jane Born "zgrepka@aol.com"

ENV version=docker
ENV dbuser=
ENV dbpass=
ENV jdbcurl=

WORKDIR /usr/local/bin

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]