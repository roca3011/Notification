package com.example.notificationeventhub.adapter.azure.exceptions;

import com.example.notificationeventhub.config.ErrorCode;

public class NotificationEventException extends RuntimeException {

    private final ErrorCode errorCode;

    public NotificationEventException(ErrorCode errorCode) {
        super(errorCode.getReasonPhrase());
        this.errorCode = errorCode;
    }
}
