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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderRestControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "http://localhost:";

    @Test
    public void getAllOrdersTest() {

        @SuppressWarnings("unchecked")
        ResponseEntity<List<Order>> res = this.restTemplate
                .getForEntity(url + port + "/api/orders/",
                        (Class<List<Order>>)(Object)List.class);

        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, res.getStatusCode().series());
    }

    @Test
    public void getOrderByIdTest() {
        /*
         * test valid query
         */
        ResponseEntity<Order> res = this.restTemplate
                .getForEntity(url + port + "/api/orders/1", Order.class);

        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, res.getStatusCode().series());

        /*
         * test bad query
         */
        ResponseEntity<Order> resErr = this.restTemplate
                .getForEntity(url + port + "/api/orders/-1", Order.class);

        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR, resErr.getStatusCode().series());
    }

    @Test
    public void getOrdersByUserIdTest() {

        @SuppressWarnings("unchecked")
        ResponseEntity<List<Order>> res = this.restTemplate
                .getForEntity(url + port + "/api/users/1/orders", (Class<List<Order>>)(Object)List.class);

        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, res.getStatusCode().series());
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

        ResponseEntity<Order> res = this.restTemplate
                .postForEntity(url + port + "/api/orders", order, Order.class);

        Assertions.assertEquals(res.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(order.getStatus(), Objects.requireNonNull(res.getBody()).getStatus());

        /*
         * remove order
         */
        this.restTemplate.delete(url + port + "/api/orders/" + res.getBody().getId());
    }

    @Test
    public void updateOrderTest() {
        /*
         *  update order
         */

        Order order = new Order();
        order.setStatus(OrderStatus.CANCELED);

        ResponseEntity<Order> res = this.restTemplate
                .postForEntity(url + port + "/api/orders/2", order, Order.class);

        Assertions.assertEquals(res.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(order.getStatus(), Objects.requireNonNull(res.getBody()).getStatus());

        /*
         * rollback order
         */
        order.setStatus(OrderStatus.CREATED);
        ResponseEntity<Order> res_2 = this.restTemplate
                .postForEntity(url + port + "/api/orders/2", order, Order.class);

        Assertions.assertEquals(res_2.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(order.getStatus(), Objects.requireNonNull(res_2.getBody()).getStatus());

        /*
         * test bad query
         */
        ResponseEntity<Order> res_3 = this.restTemplate
                .postForEntity(url + port + "/api/orders/-2", order, Order.class);

        Assertions.assertEquals(res_3.getStatusCode().series(), HttpStatus.Series.CLIENT_ERROR);

    }

}
