package com.hexagon.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexagon.service.processor.EventsProcessor;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.IEventProcessorFactory;
import com.microsoft.azure.eventprocessorhost.PartitionContext;
import org.springframework.stereotype.Component;

@Component
public class EventHubListenerFactory implements IEventProcessorFactory {

    private final ObjectMapper objectMapper;

    private final EventsProcessor eventsProcessor;

    public EventHubListenerFactory(ObjectMapper objectMapper, EventsProcessor eventsProcessor) {
        this.objectMapper = objectMapper;
        this.eventsProcessor = eventsProcessor;
    }

    @Override
    public IEventProcessor createEventProcessor(PartitionContext context) {
        return new EventReceiver(objectMapper, eventsProcessor);
    }
}
