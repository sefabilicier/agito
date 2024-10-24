FROM openjdk:22
VOLUME /tmp
WORKDIR /app
EXPOSE 8080
ADD agitoo/target/agitoo-0.0.1-SNAPSHOT.jar agitoo-0.0.1-SNAPSHOT.jar
LABEL authors="sefa.bilicier"

LABEL authors="sefa.biliciler" \
      version="0.0.1-SNAPSHOT" \
      description="Agitoo is a sample Spring Boot application." \
      maintainer="sefa.bilicier@agito.com" \
      license="MIT" \
      vendor="Agitoo Inc." \
      usage="Run the application with 'docker run -p 8080:8080 agitoo:latest'" \
      tags="spring, docker, java"

ENTRYPOINT ["java", "-jar", "agitoo-0.0.1-SNAPSHOT.jar"]