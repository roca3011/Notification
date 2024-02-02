package com.example.notificationeventhub.application.usecase;

import com.example.notificationeventhub.application.port.NotificationEvent;
import com.example.notificationeventhub.application.port.SendNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendNotificationUseCase implements SendNotification {

    private final NotificationEvent notificationEvent;

    public SendNotificationUseCase(NotificationEvent notificationEvent) {
        this.notificationEvent = notificationEvent;
    }

    @Override
    public void execute(String message){
        log.info("Starting send notification usecase");
        notificationEvent.sendMessage(message);
        log.info("Finished send notification usecase");
    }
}
