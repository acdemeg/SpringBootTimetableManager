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

import java.util.List;
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

        @SuppressWarnings("unchecked")
        ResponseEntity<List<User>> res = this.restTemplate
                .getForEntity(url + port + "/api/users/",
                        (Class<List<User>>)(Object)List.class);

        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, res.getStatusCode().series());
    }

    @Test
    public void getUserByIdTest() {
        /*
         * test valid query
         */
        ResponseEntity<User> res = this.restTemplate
                .getForEntity(url + port + "/api/users/1", User.class);

        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, res.getStatusCode().series());

        /*
         * test bad query
         */
        ResponseEntity<User> resErr = this.restTemplate
                .getForEntity(url + port + "/api/users/-1", User.class);

        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR, resErr.getStatusCode().series());
    }

    @Test
    public void regAndDeleteUserTest() {
        /*
         *  registration
         */
        User user = new User("TestSpringUser", "ptr@gmail.com", "hardPass",
                null, null, null, null);

        ResponseEntity<User> res = this.restTemplate
                .postForEntity(url + port + "/api/users/register", user, User.class);

        Assertions.assertEquals(res.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(user.getEmail(), Objects.requireNonNull(res.getBody()).getEmail());

        /*
         * remove user
         */
         this.restTemplate.delete(url + port + "/api/users/" + res.getBody().getId());
    }

    @Test
    public void updateUserProfileTest() {
        User user = new User();
        user.setName("Michael");
        /*
         * test valid query
         */
        ResponseEntity<User> res = this.restTemplate
                .postForEntity(url + port + "/api/users/3", user, User.class);

        Assertions.assertEquals(res.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(user.getName(), Objects.requireNonNull(res.getBody()).getName());
        /*
         * test bad query
         */
        ResponseEntity<User> resError = this.restTemplate
                .postForEntity(url + port + "/api/users/-1", user, User.class);
        Assertions.assertEquals(resError.getStatusCode().series(), HttpStatus.Series.CLIENT_ERROR);

    }
}
