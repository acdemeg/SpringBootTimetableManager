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
import java.util.List;
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

        /*
         * Anonymous authorization
         */

        series = makeTestGET("");
        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR, series);

        /*
         * User(Not Owner) credentials
         */
        series = makeTestGET("Basic TWljbGVAbWFpbC5ydTpNaWNsZV9wYXNzdw==");
        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR, series);

        /*
         * Admin credentials
         */
        series = makeTestGET("Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==");
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, series);

        /*
         * User(Owner) credentials
         */
        series = makeTestGET("Basic am9vQGdvb2dsZS5jb206am9vX3Bhc3N3");
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, series);
    }

    @Test
    public void updateNotificationTest() {
        Notification notification = new Notification();
        notification.setType(NotificationType.ORDER_CREATED);
        /*
         * Admin credentials
         */
        ResponseEntity<?> res = makeTestPOST("Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==", notification);
        @SuppressWarnings("unchecked")
        String resType = ((LinkedHashMap<String, String>) Objects.requireNonNull(res.getBody())).get("type");
        Assertions.assertEquals(notification.getType().name(), resType);
    }

    private HttpStatus.Series makeTestGET(String BasicAuth){
        TestUtilities.setAuthorizationHeader(this.restTemplate, BasicAuth);
        String api = url + port + "/api/users/2/notifications";
        return this.restTemplate.getForEntity(api, Object.class).getStatusCode().series();
    }

    private ResponseEntity<?> makeTestPOST(String BasicAuth, Object payload){
        TestUtilities.setAuthorizationHeader(this.restTemplate, BasicAuth);
        String api = url + port + "/api/notifications/1";
        return this.restTemplate.postForEntity(api, payload, Object.class);
    }

}
