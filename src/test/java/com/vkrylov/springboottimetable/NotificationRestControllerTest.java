package com.vkrylov.springboottimetable;

import com.vkrylov.springboottimetable.entities.Notification;
import com.vkrylov.springboottimetable.entities.NotificationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "http://localhost:";

    @Test
    public void getNotificationsByUserIdTest() {

        HttpStatus.Series series;
        String apiURL = url + port + "/api/users/2/notifications";

        /*
         * Anonymous authorization
         */
        series = TestUtilities.makeTestGET("", this.restTemplate, apiURL);
        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR, series);

        /*
         * User(Not Owner) credentials
         */
        series = TestUtilities.makeTestGET(
                "Basic TWljbGVAbWFpbC5ydTpNaWNsZV9wYXNzdw==", this.restTemplate, apiURL
        );
        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR, series);

        /*
         * Admin credentials
         */
        series = TestUtilities.makeTestGET(
                "Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==", this.restTemplate, apiURL
        );
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, series);

        /*
         * User(Owner) credentials
         */
        series = TestUtilities.makeTestGET(
                "Basic am9vQGdvb2dsZS5jb206am9vX3Bhc3N3", this.restTemplate, apiURL
        );
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, series);
    }

    @Test
    public void updateNotificationTest() {
        Notification notification = new Notification();
        notification.setType(NotificationType.ORDER_CREATED);
        /*
         * Admin credentials
         */
        ResponseEntity<?> res = TestUtilities.makeTestPOST("Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==",
                notification, this.restTemplate, url + port + "/api/notifications/1");
        @SuppressWarnings("rawtypes")
        String resType = (String) ((LinkedHashMap) Objects.requireNonNull(res.getBody())).get("type");
        Assertions.assertEquals(notification.getType().name(), resType);
    }
}
