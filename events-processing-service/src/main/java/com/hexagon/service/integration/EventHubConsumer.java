package com.hexagon.service.integration;

import com.microsoft.azure.eventprocessorhost.EventProcessorHost;
import com.microsoft.azure.eventprocessorhost.EventProcessorOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutionException;

@Component
public class EventHubConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventHubConsumer.class);

    private final EventProcessorHost eventProcessorHost;

    private final EventHubListenerFactory eventHubListenerFactory;

    public EventHubConsumer(EventProcessorHost eventProcessorHost, EventHubListenerFactory eventHubListenerFactory) {
        this.eventProcessorHost = eventProcessorHost;
        this.eventHubListenerFactory = eventHubListenerFactory;
    }

    @PostConstruct
    public void consumeEvents() throws ExecutionException, InterruptedException {
        eventProcessorHost.registerEventProcessorFactory(eventHubListenerFactory, new EventProcessorOptions())
                .whenComplete(
                        (unused, e) -> {
                            if (e != null) {
                                LOGGER.error("Failure while registering Event Hub listener", e);
                                if (e.getCause() != null) {
                                    LOGGER.error("Inner exception while registering Event Hub listener", e);
                                }
                            } else {
                                LOGGER.info("Successfully registered Event Hub listener");
                            }
                        })
                .get();
    }
}
