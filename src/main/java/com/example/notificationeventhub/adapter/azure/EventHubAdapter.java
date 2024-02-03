package com.example.notificationeventhub.adapter.azure;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.example.notificationeventhub.adapter.azure.exceptions.NotificationEventException;
import com.example.notificationeventhub.application.port.NotifyEvent;
import com.example.notificationeventhub.config.ErrorCode;
import com.example.notificationeventhub.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventHubAdapter implements NotifyEvent {

    private final EventHubClientBuilder eventHubClientBuilder;
    private final ObjectMapper objectMapper;

    @Value("${azure.eventhubs.connection-string}")
    private String connectionString;
    @Value("${azure.eventhubs.eventhub-name}")
    private String eventHubName;

    public EventHubAdapter(EventHubClientBuilder eventHubClientBuilder, ObjectMapper objectMapper) {
        this.eventHubClientBuilder = eventHubClientBuilder;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendEvent(Message message) {
        log.info("Starting sendEvent");
        try (EventHubProducerClient producer = eventHubClientBuilder
                .connectionString(connectionString, eventHubName)
                .buildProducerClient()) {
            var stringMessage = objectMapper.writeValueAsString(message);
            var eventData = new EventData(stringMessage);
            var eventDataBatch = producer.createBatch();
            if (eventDataBatch.tryAdd(eventData)) {
                producer.send(eventDataBatch);
            }
            log.info("Finished sendEvent");
        } catch (Exception e) {
            log.error(ErrorCode.NOTIFICATION_EVENT_ERROR.getReasonPhrase(), e);
            throw new NotificationEventException(ErrorCode.NOTIFICATION_EVENT_ERROR);
        }
    }
}
