package com.example.notificationeventhub.adapter.azure;

import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("EventHubAdapter Test")
@ExtendWith(MockitoExtension.class)
class NotificationEventHubTest {

    @Mock
    private EventHubClientBuilder eventHubClientBuilder;

    @Mock
    private EventHubProducerClient producer;

    @Mock
    private EventDataBatch eventDataBatch;


    @InjectMocks
    private NotificationEventHub notificationEventHub;

    @Test
    @DisplayName("when recibe message then sent notification, ok")
    void whenNotifyWhenUpdatingOk(){
        var message = "Hello World";
        when(eventHubClientBuilder.connectionString(any(), any())).thenReturn(eventHubClientBuilder);
        when(eventHubClientBuilder.buildProducerClient()).thenReturn(producer);
        when(producer.createBatch()).thenReturn(eventDataBatch);
        when(eventDataBatch.tryAdd(any())).thenReturn(true);
        notificationEventHub.sendMessage(message);
        verify(producer, times(1)).createBatch();
        verify(producer, times(1)).send((EventDataBatch) any());
    }

}