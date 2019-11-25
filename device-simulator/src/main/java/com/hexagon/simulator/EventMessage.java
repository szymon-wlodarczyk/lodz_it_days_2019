package com.hexagon.simulator;

import java.util.Objects;

public class EventMessage {

    private String eventOwner;

    private String payload;

    private long timestamp;

    public String getEventOwner() {
        return eventOwner;
    }

    public void setEventOwner(String eventOwner) {
        this.eventOwner = eventOwner;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventMessage that = (EventMessage) o;
        return timestamp == that.timestamp &&
                Objects.equals(eventOwner, that.eventOwner) &&
                Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventOwner, payload, timestamp);
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "eventOwner='" + eventOwner + '\'' +
                ", payload='" + payload + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
