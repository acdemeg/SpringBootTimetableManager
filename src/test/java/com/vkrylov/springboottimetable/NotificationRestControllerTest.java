package com.vkrylov.springboottimetable;

import com.vkrylov.springboottimetable.entity.Notification;
import com.vkrylov.springboottimetable.entity.NotificationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "http://localhost:";

    @Test
    public void getNotificationsByUserIdTest() {

        @SuppressWarnings("unchecked")
        ResponseEntity<List<Notification>> res = this.restTemplate
                .getForEntity(url + port + "/api/users/2/notifications",
                (Class<List<Notification>>)(Object)List.class);

        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, res.getStatusCode().series());
    }

    @Test
    public void updateNotificationTest() {
        Notification notification = new Notification();
        notification.setType(NotificationType.ORDER_CREATED);

        Notification res = this.restTemplate
                .postForObject(url + port + "/api/notifications/1", notification,
                        Notification.class);

        Assertions.assertEquals(notification.getType(), res.getType());
    }

}
