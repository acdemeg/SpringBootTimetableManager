package com.vkrylov.springboottimetable;

import com.vkrylov.springboottimetable.entities.AttributeValue;
import com.vkrylov.springboottimetable.entities.Order;
import com.vkrylov.springboottimetable.entities.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "http://localhost:";

    @Test
    public void getAllOrdersTest() {
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL,
                TestUtilities.makeTestGET("", this.restTemplate, url + port + "/api/orders/"));
    }

    @Test
    public void getOrderByIdTest() {
        /*
         * test valid query
         */
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL,
                TestUtilities.makeTestGET("", this.restTemplate, url + port + "/api/orders/1"));
        /*
         * test bad query
         */
        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR,
                TestUtilities.makeTestGET("", this.restTemplate, url + port + "/api/orders/-1"));
    }

    @Test
    public void getOrdersByUserIdTest() {
        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL,
                TestUtilities.makeTestGET("", this.restTemplate, url + port + "/api/users/1/orders"));
    }

    @Test
    public void createAndDeleteOrderTest() {
        /*
         *  create order
         */
        AttributeValue attr = new AttributeValue(1, 0, 1, "JS Challenge");
        AttributeValue attr_2 = new AttributeValue(2, 0, 1, "20");
        List<AttributeValue> list = new ArrayList<>(Arrays.asList(attr, attr_2));

        Order order = new Order(1, "Admin", ZonedDateTime.parse("2020-04-11T16:00:00.000Z"),
                ZonedDateTime.parse("2020-04-11T17:00:00.000Z"), null, 1, list, null);
        /*
         * test valid query
         */
        ResponseEntity<?> res = TestUtilities.makeTestPOST("Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==",
                order, this.restTemplate, url + port + "/api/orders");
        @SuppressWarnings("rawtypes")
        Integer id = (Integer) ((LinkedHashMap) Objects.requireNonNull(res.getBody())).get("id");
        Assertions.assertEquals(res.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        /*
         * remove order
         */
        this.restTemplate.delete(url + port + "/api/orders/" + id.toString());

        /*
         * test bad query
         */
        order.setAuthorId(3);
        ResponseEntity<?> resBad = TestUtilities.makeTestPOST("Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==",
                order, this.restTemplate, url + port + "/api/orders");

        Assertions.assertEquals(resBad.getStatusCode().series(), HttpStatus.Series.CLIENT_ERROR);
    }

    @Test
    public void updateOrderTest() {
        /*
         *  update order
         */
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        /*
         * test valid query
         */
        ResponseEntity<?> res = TestUtilities.makeTestPOST("Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==",
                order, this.restTemplate, url + port + "/api/orders/24");
        @SuppressWarnings("rawtypes")
        String status = (String) ((LinkedHashMap) Objects.requireNonNull(res.getBody())).get("status");

        Assertions.assertEquals(res.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(order.getStatus().name(), status);
        /*
         * test bad query
         */
        ResponseEntity<?> resBad = TestUtilities.makeTestPOST("Basic am9vQGdvb2dsZS5jb206am9vX3Bhc3N3",
                order, this.restTemplate, url + port + "/api/orders/24");

        Assertions.assertEquals(resBad.getStatusCode().series(), HttpStatus.Series.CLIENT_ERROR);

    }

}
