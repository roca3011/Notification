package com.example.notificationeventhub.config;

import com.azure.messaging.eventhubs.EventHubClientBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EventHubConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public EventHubClientBuilder getEventHubClientBuilder(){
        return new EventHubClientBuilder();
    }
}
