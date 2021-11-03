package com.vkrylov.springboottimetable;

import com.vkrylov.springboottimetable.entities.Attribute;
import com.vkrylov.springboottimetable.entities.TimeTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TimeTableRestControllerTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String url = "http://localhost:";
    
    
    @Test
    public void getAllTimeTablesTest() {

        @SuppressWarnings("unchecked")
        ResponseEntity<List<TimeTable>> res = this.restTemplate
                .getForEntity(url + port + "/api/timetables/",
                        (Class<List<TimeTable>>)(Object)List.class);

        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, res.getStatusCode().series());
    }

    @Test
    public void getTimeTableByIdTest() {
        /*
         * test valid query
         */
        ResponseEntity<TimeTable> res = this.restTemplate
                .getForEntity(url + port + "/api/timetables/1", TimeTable.class);

        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, res.getStatusCode().series());

        /*
         * test bad query
         */
        ResponseEntity<TimeTable> resErr = this.restTemplate
                .getForEntity(url + port + "/api/timetables/-1", TimeTable.class);

        Assertions.assertEquals(HttpStatus.Series.CLIENT_ERROR, resErr.getStatusCode().series());
    }

    @Test
    public void createAndDeleteTimeTableTest() {
        /*
         *  create timeTable
         */
        Attribute attribute = new Attribute("Name Event", "STRING", true, 0);
        TimeTable timeTable = new TimeTable("Meeting", "2020-04-09T18:00:00.000Z",
                "2020-04-09T18:00:00.000Z", "HOUR");
        timeTable.setAttributes(new ArrayList<>(Collections.singletonList(attribute)));

        ResponseEntity<TimeTable> res = this.restTemplate
                .postForEntity(url + port + "/api/timetables", timeTable, TimeTable.class);

        Assertions.assertEquals(res.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(timeTable.getTitle(), Objects.requireNonNull(res.getBody()).getTitle());

        /*
         * remove timeTable
         */
        this.restTemplate.delete(url + port + "/api/timetables/" + res.getBody().getId());
    }

    @Test
    public void updateTimeTableTest() {
        TimeTable timeTable = new TimeTable();
        timeTable.setTitle("HR API Online-marathon 2020");
        /*
         * test valid query
         */
        ResponseEntity<TimeTable> res = this.restTemplate
                .postForEntity(url + port + "/api/timetables/8", timeTable, TimeTable.class);

        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, res.getStatusCode().series());
        Assertions.assertEquals(timeTable.getTitle(), Objects.requireNonNull(res.getBody()).getTitle());
        /*
         * test bad query
         */
        ResponseEntity<TimeTable> resError = this.restTemplate
                .postForEntity(url + port + "/api/timetables/-1", timeTable, TimeTable.class);
        Assertions.assertEquals(resError.getStatusCode().series(), HttpStatus.Series.CLIENT_ERROR);

    }
}
