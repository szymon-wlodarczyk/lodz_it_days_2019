package com.hexagon.service.processor;

import com.hexagon.service.domain.EventEntity;
import com.hexagon.service.domain.EventType;
import com.hexagon.service.integration.EventMessage;
import com.hexagon.service.repository.EventsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class EventsProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsProcessor.class);

    private final EventsRepository eventsRepository;

    private final String processorId;

    public EventsProcessor(EventsRepository eventsRepository, @Value("${application.processor.id}") String processorId) {
        this.eventsRepository = eventsRepository;
        this.processorId = processorId;
    }

    public void process(EventMessage eventMessage) {
        LOGGER.info("Received following message: {}", eventMessage);
        EventEntity eventEntity = buildEventMessageEntity(eventMessage);
        LOGGER.info("Storing following entity: {}", eventEntity);
        eventsRepository.save(eventEntity);
    }

    private EventEntity buildEventMessageEntity(EventMessage eventMessage) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setDeviceTimestamp(eventMessage.getTimestamp());
        eventEntity.setEventOwner(eventMessage.getEventOwner());
        eventEntity.setDeviceTimestamp(eventMessage.getTimestamp());
        eventEntity.setEventType(EventType.DEVICE_EVENT);
        eventEntity.setServerTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        eventEntity.setProcessorId(processorId);
        eventEntity.setPayload(eventMessage.getPayload());
        return eventEntity;
    }
}
