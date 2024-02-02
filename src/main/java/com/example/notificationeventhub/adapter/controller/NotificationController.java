package com.example.notificationeventhub.adapter.controller;

import com.example.notificationeventhub.application.port.SendNotification;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/")
public class NotificationController {

    private final SendNotification sendNotification;

    public NotificationController(SendNotification sendNotification) {
        this.sendNotification = sendNotification;
    }

    @PostMapping("send")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendConnivance(final HttpServletRequest httpServletRequest,
                               @RequestBody String message) {

        sendNotification.execute(message);
    }
}
