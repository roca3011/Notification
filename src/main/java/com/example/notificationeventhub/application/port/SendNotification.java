package com.example.notificationeventhub.application.port;

import com.example.notificationeventhub.model.Message;

public interface SendNotification {

    void execute(Message message);
}
