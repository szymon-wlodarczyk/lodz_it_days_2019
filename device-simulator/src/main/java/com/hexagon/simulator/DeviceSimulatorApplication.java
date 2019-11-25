package com.hexagon.simulator;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class DeviceSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceSimulatorApplication.class, args);
    }

    @Bean
    public DeviceClient deviceClient(@Value("${iothub.connection.string}") String iotHubConnectionString) throws URISyntaxException {
        return new DeviceClient(iotHubConnectionString, IotHubClientProtocol.MQTT);
    }

}
