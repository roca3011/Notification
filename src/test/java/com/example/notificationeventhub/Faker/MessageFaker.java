package com.example.notificationeventhub.Faker;

import com.example.notificationeventhub.model.Message;

public class MessageFaker {

    public static Message getMessageFaker() {
        return Message.builder()
                .recipient("123@email.com")
                .subject("Test")
                .body("Hello world")
                .build();
    }
}
