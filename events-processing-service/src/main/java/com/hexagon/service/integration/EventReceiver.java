package com.hexagon.service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexagon.service.processor.EventsProcessor;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventprocessorhost.CloseReason;
import com.microsoft.azure.eventprocessorhost.IEventProcessor;
import com.microsoft.azure.eventprocessorhost.PartitionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class EventReceiver implements IEventProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventReceiver.class);

    private final ObjectMapper objectMapper;

    private final EventsProcessor eventsProcessor;

    public EventReceiver(ObjectMapper objectMapper, EventsProcessor eventsProcessor) {
        this.objectMapper = objectMapper;
        this.eventsProcessor = eventsProcessor;
    }

    @Override
    public void onOpen(PartitionContext context) throws Exception {
        LOGGER.info("Receiver opened on partition: {}", context.getPartitionId());
    }

    @Override
    public void onClose(PartitionContext context, CloseReason reason) throws Exception {
        LOGGER.warn("Receiver closed on partition: {}, reason: {}", context.getPartitionId(), reason);
    }

    @Override
    public void onEvents(PartitionContext context, Iterable<EventData> events) {
        int eventCount = 0;
        for (EventData data : events) {
            try {
                eventCount++;
                String messageAsString = new String(data.getBytes(), StandardCharsets.UTF_8);
                LOGGER.info("Received event data {}", messageAsString);
                EventMessage eventMessage = objectMapper.readValue(messageAsString, EventMessage.class);
                eventsProcessor.process(eventMessage);
                context.checkpoint(data).get();
            } catch (Exception exception) {
                LOGGER.error("Error while consuming event", exception);
            }
        }
        LOGGER.info("Received {} events in batch", eventCount);
    }

    @Override
    public void onError(PartitionContext context, Throwable error) {
        LOGGER.error("Receiver error on partition: {}", context.getPartitionId(), error);
    }
}
