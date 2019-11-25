# Getting Started
Code simulates data send over MQTT protocol to Azure IoT Hub.

### Prerequisite
* Installed openjdk 11
* Configure Azure IoT Hub connection string for simulated device in application.properties file
```device-simulator/src/main/resources/application.properties```

### Build & run
Test runs application which sends messages to configured IoT Hub endpoint
```./gradlew clean build```

