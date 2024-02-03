package com.example.notificationeventhub.application.port;

import com.example.notificationeventhub.model.Message;

public interface NotifyEvent {

    void sendEvent(Message message);
}
