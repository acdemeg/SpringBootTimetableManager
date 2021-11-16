package com.vkrylov.springboottimetable;

import com.vkrylov.springboottimetable.entities.User;
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
public class UserRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "http://localhost:";

    @Test
    public void getAllUsersTest() {
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL,
                TestUtilities.makeTestGET("", this.restTemplate, url + port + "/api/users/"));
    }

    @Test
    public void getUserByIdTest() {
        /*
         * test valid query
         */
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL,
                TestUtilities.makeTestGET("", this.restTemplate, url + port + "/api/users/1"));

        /*
         * test bad query
         */
        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR,
                TestUtilities.makeTestGET("", this.restTemplate, url + port + "/api/users/-1"));
    }

    @Test
    public void regAndDeleteUserTest() {
        /*
         *  registration
         */
        User user = new User(0, "TestSpringUser", "ptr@gmail.com", "hardPass",
                null, null, null, null);


        ResponseEntity<?> res = TestUtilities.makeTestPOST(
                "", user, this.restTemplate, url + port + "/api/users/register");
        @SuppressWarnings("rawtypes")
        String email = (String) ((LinkedHashMap) Objects.requireNonNull(res.getBody())).get("email");
        @SuppressWarnings("rawtypes")
        Integer id = (Integer) ((LinkedHashMap) Objects.requireNonNull(res.getBody())).get("id");

        Assertions.assertEquals(res.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(user.getEmail(), email);

        /*
         * remove user
         */
         TestUtilities.setAuthorizationHeader(
                 this.restTemplate, "Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==");
         this.restTemplate.delete(url + port + "/api/users/" + id.toString());
    }

    @Test
    public void updateUserProfileTest() {
        User user = new User();
        user.setName("Michael");
        /*
         * test valid query
         */
        ResponseEntity<?> res = TestUtilities.makeTestPOST("Basic TWljbGVAbWFpbC5ydTpNaWNsZV9wYXNzdw==",
                user, this.restTemplate, url + port + "/api/users/9");
        @SuppressWarnings("rawtypes")
        String name = (String) ((LinkedHashMap) Objects.requireNonNull(res.getBody())).get("name");

        Assertions.assertEquals(res.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(user.getName(), name);
        /*
         * test bad query
         */
        ResponseEntity<?> resError = TestUtilities.makeTestPOST("Basic TWljbGVAbWFpbC5ydTpNaWNsZV9wYXNzdw==",
                user, this.restTemplate, url + port + "/api/users/11");
        Assertions.assertEquals(resError.getStatusCode().series(), HttpStatus.Series.CLIENT_ERROR);

    }
}
