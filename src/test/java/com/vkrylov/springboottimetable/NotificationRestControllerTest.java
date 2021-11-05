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
        series = makeTest("", HttpStatus.Series.CLIENT_ERROR);
        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR, series);

        /*
         * User(Not Owner) credentials
         */
        series = makeTest("Basic TWljbGVAbWFpbC5ydTpNaWNsZV9wYXNzdw==", HttpStatus.Series.CLIENT_ERROR);
        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR, series);

        /*
         * Admin credentials
         */
        series = makeTest("Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==", HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, series);

        /*
         * User(Owner) credentials
         */
        series = makeTest("Basic am9vQGdvb2dsZS5jb206am9vX3Bhc3N3", HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, series);
    }

    @Test
    public void updateNotificationTest() {
        Notification notification = new Notification();
        notification.setType(NotificationType.ORDER_CREATED);

        TestUtilities.setAuthorizationHeader(
                this.restTemplate, "Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==");

        ResponseEntity<Notification> res = this.restTemplate
                .postForEntity(url + port + "/api/notifications/1", notification,
                        Notification.class);

        Assertions.assertEquals(notification.getType(), Objects.requireNonNull(res.getBody()).getType());
    }

    @SuppressWarnings("unchecked")
    private HttpStatus.Series makeTest(String BasicAuth, HttpStatus.Series series){

        TestUtilities.setAuthorizationHeader(this.restTemplate, BasicAuth);
        String api = url + port + "/api/users/2/notifications";

        if(series.equals(HttpStatus.Series.CLIENT_ERROR)){
            return this.restTemplate.getForEntity(api, String.class).getStatusCode().series();
        }
        else {
            return this.restTemplate.getForEntity(api,
                    (Class<List<Notification>>)(Object)List.class).getStatusCode().series();
        }
    }

}
