package com.example.notificationeventhub.application.usecase;

import com.example.notificationeventhub.application.port.NotifyEvent;
import com.example.notificationeventhub.application.port.SendNotification;
import com.example.notificationeventhub.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendNotificationUseCase implements SendNotification {

    private final NotifyEvent notifyEvent;

    public SendNotificationUseCase(NotifyEvent notifyEvent) {
        this.notifyEvent = notifyEvent;
    }

    @Override
    public void execute(Message message){
        log.info("Starting send notification usecase");
        notifyEvent.sendEvent(message);
        log.info("Finished send notification usecase");
    }
}
