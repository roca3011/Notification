package com.example.notificationeventhub.adapter.azure;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.example.notificationeventhub.adapter.azure.exceptions.NotificationEventException;
import com.example.notificationeventhub.application.port.NotificationEvent;
import com.example.notificationeventhub.config.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventHub implements NotificationEvent {

    private final EventHubClientBuilder eventHubClientBuilder;

    @Value("${azure.eventhubs.connection-string}")
    private String connectionString;
    @Value("${azure.eventhubs.eventhub-name}")
    private String eventHubName;

    public NotificationEventHub(EventHubClientBuilder eventHubClientBuilder) {
        this.eventHubClientBuilder = eventHubClientBuilder;
    }

    @Override
    public void sendMessage(String message) {
        try (EventHubProducerClient producer = eventHubClientBuilder
                .connectionString(connectionString, eventHubName)
                .buildProducerClient()) {
            var eventData = new EventData(message);
            var eventDataBatch = producer.createBatch();
            if (eventDataBatch.tryAdd(eventData)) {
                producer.send(eventDataBatch);
            }
        } catch (Exception e) {
            throw new NotificationEventException(ErrorCode.NOTIFICATION_EVENT_ERROR);
        }
    }
}
