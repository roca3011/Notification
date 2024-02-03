package com.example.notificationeventhub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Message {

    @JsonProperty("destinatario")
    String recipient;
    @JsonProperty("asunto")
    String subject;
    @JsonProperty("mensaje")
    String body;
}
