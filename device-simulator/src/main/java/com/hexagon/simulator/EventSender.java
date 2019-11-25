package com.hexagon.simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

@Service
public class EventSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventSender.class);

    private final DeviceClient deviceClient;

    private final ObjectMapper objectMapper;

    private final int numberOfEvents;

    public EventSender(DeviceClient deviceClient,
                       ObjectMapper objectMapper,
                       @Value("${number.of.events}") int numberOfEvents) {
        this.deviceClient = deviceClient;
        this.objectMapper = objectMapper;
        this.numberOfEvents = numberOfEvents;
    }

    @PostConstruct
    public void sendEventsToIoTHub() throws IOException, InterruptedException {
        deviceClient.open();
        for (int seqNr = 1; seqNr <= numberOfEvents; seqNr++) {
            EventMessage message = buildEventMessage(seqNr);
            String messageString = objectMapper.writeValueAsString(message);
            LOGGER.info("Sending message: {}", messageString);
            deviceClient.sendEventAsync(new Message(messageString), null, null);

            // let's wait until messages are send to Azure IoT Hub
            TimeUnit.SECONDS.sleep(3);
        }

    }

    private EventMessage buildEventMessage(int sequenceNumber) {
        EventMessage eventMessage = new EventMessage();
        eventMessage.setPayload("test payload; seq nr: " + sequenceNumber);
        eventMessage.setEventOwner("Szymon");
        eventMessage.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        return eventMessage;
    }
}
