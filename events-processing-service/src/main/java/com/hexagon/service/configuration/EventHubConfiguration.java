package com.hexagon.service.configuration;

import com.microsoft.azure.eventhubs.ConnectionStringBuilder;
import com.microsoft.azure.eventprocessorhost.EventProcessorHost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventHubConfiguration {

    @Value("${eventHub.host.name}")
    private String eventHubHostName;

    @Value("${eventHub.name}")
    private String eventHubName;

    @Value("${eventHub.consumer.group.name}")
    private String eventHubConsumerGroupName;

    @Value("${eventHub.namespace.name}")
    private String namespaceName;

    @Value("${eventHub.sas.keyName}")
    private String sasKeyName;

    @Value("${eventHub.sas.key}")
    private String sasKey;

    @Value("${eventHub.storage.connectionString}")
    private String storageConnectionString;

    @Value("${eventHub.storage.containerName}")
    private String storageContainerName;

    @Bean
    public EventProcessorHost eventProcessorHost() {
        ConnectionStringBuilder eventHubConnectionString = new ConnectionStringBuilder()
                .setNamespaceName(namespaceName)
                .setEventHubName(eventHubName)
                .setSasKeyName(sasKeyName)
                .setSasKey(sasKey);
        return EventProcessorHost.EventProcessorHostBuilder
                .newBuilder(EventProcessorHost.createHostName(eventHubHostName), eventHubConsumerGroupName)
                .useAzureStorageCheckpointLeaseManager(storageConnectionString, storageContainerName, null)
                .useEventHubConnectionString(eventHubConnectionString.toString(), eventHubName)
                .build();
    }
}
