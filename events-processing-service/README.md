# Getting Started
Spring Boot application which reads incoming messages from Azure Event Hub and stores them in SQL database.

### Prerequisite
* Installed openjdk 11
* Docker version 18.09.7
* Configure SQL database connection details and Azure Event Hub parameters
```events-processing-service/src/main/resources/application.properties```

* Test configuration
```events-processing-service/src/test/resources/application.properties```

### Build & run
Test runs application which sends messages to configured IoT Hub endpoint
```./gradlew clean build```

### Docker commands
Bulding docker image
```docker build . –t events-processing-service```

Running docker container
```docker run -d events-processing-service```

