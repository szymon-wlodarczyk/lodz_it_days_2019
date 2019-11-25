package com.hexagon.service.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "events", schema = "ldischema")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_sequence")
    @SequenceGenerator(name = "events_sequence", sequenceName = "ldischema.events_seq")
    private long id;

    @Column(name = "event_owner")
    private String eventOwner;

    @Column(name = "device_timestamp")
    private long deviceTimestamp;

    @Column(name = "server_timestamp")
    private long serverTimestamp;

    @Column(name = "payload")
    private String payload;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "processor_id")
    private String processorId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(String eventOwner) {
        this.eventOwner = eventOwner;
    }

    public long getDeviceTimestamp() {
        return deviceTimestamp;
    }

    public void setDeviceTimestamp(long deviceTimestamp) {
        this.deviceTimestamp = deviceTimestamp;
    }

    public long getServerTimestamp() {
        return serverTimestamp;
    }

    public void setServerTimestamp(long serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setProcessorId(String processorId) {
        this.processorId = processorId;
    }

    public String getProcessorId() {
        return processorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return id == that.id &&
                deviceTimestamp == that.deviceTimestamp &&
                serverTimestamp == that.serverTimestamp &&
                Objects.equals(eventOwner, that.eventOwner) &&
                Objects.equals(payload, that.payload) &&
                eventType == that.eventType &&
                Objects.equals(processorId, that.processorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventOwner, deviceTimestamp, serverTimestamp, payload, eventType, processorId);
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", eventOwner='" + eventOwner + '\'' +
                ", deviceTimestamp=" + deviceTimestamp +
                ", serverTimestamp=" + serverTimestamp +
                ", payload='" + payload + '\'' +
                ", eventType=" + eventType +
                ", processorId='" + processorId + '\'' +
                '}';
    }
}
