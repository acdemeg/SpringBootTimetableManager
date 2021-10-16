package com.vkrylov.springboottimetable;

import com.vkrylov.springboottimetable.rest.NotificationRestController;
import com.vkrylov.springboottimetable.rest.OrderRestController;
import com.vkrylov.springboottimetable.rest.TimeTableRestController;
import com.vkrylov.springboottimetable.rest.UserRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootTimetableApplicationTests {

    @Autowired
    private NotificationRestController notificationRestController;
    @Autowired
    private OrderRestController orderRestController;
    @Autowired
    private TimeTableRestController timeTableRestController;
    @Autowired
    private UserRestController userRestController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(notificationRestController);
        Assertions.assertNotNull(orderRestController);
        Assertions.assertNotNull(timeTableRestController);
        Assertions.assertNotNull(userRestController);
    }

}
