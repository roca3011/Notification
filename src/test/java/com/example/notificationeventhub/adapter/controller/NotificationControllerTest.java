package com.example.notificationeventhub.adapter.controller;

import com.example.notificationeventhub.Faker.MessageFaker;
import com.example.notificationeventhub.application.usecase.SendNotificationUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Notification controller Test")
@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SendNotificationUseCase sendEvent;

    @Test
    @DisplayName("When received message send to notification hub, the endpoint must return 204")
    void whenReceivedClientidAndSendConnivenceThenReturn204() throws Exception {
        var stringMessage = objectMapper.writeValueAsString(MessageFaker.getMessageFaker());
        doNothing().when(sendEvent).execute(any());
        mockMvc
                .perform(
                        post("/api/v1.0/send/event")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stringMessage)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(204));
    }

}