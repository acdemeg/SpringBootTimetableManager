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
import java.util.LinkedHashMap;
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
        Assertions.assertEquals(
                HttpStatus.Series.SUCCESSFUL,
                TestUtilities.makeTestGET("", this.restTemplate, url + port + "/api/timetables/")
        );
    }

    @Test
    public void getTimeTableByIdTest() {
        /*
         * test valid query
         */
        Assertions.assertEquals(
                HttpStatus.Series.SUCCESSFUL,
                TestUtilities.makeTestGET("", this.restTemplate, url + port + "/api/timetables/1")
        );

        /*
         * test bad query
         */
        Assertions.assertEquals(
                HttpStatus.Series.CLIENT_ERROR,
                TestUtilities.makeTestGET("", this.restTemplate, url + port + "/api/timetables/-1")
        );
    }

    @Test
    public void createAndDeleteTimeTableTest() {
        /*
         *  create timeTable
         */
        Attribute attribute = new Attribute(0, "Name Event", "STRING", true, 0);
        TimeTable timeTable = new TimeTable(0, "Meeting", "2020-04-09T18:00:00.000Z",
                "2020-04-09T18:00:00.000Z", "HOUR", null, null);
        timeTable.setAttributes(new ArrayList<>(Collections.singletonList(attribute)));

        String apiURL = url + port + "/api/timetables/";
        /*
         * Admin credentials
         */
        ResponseEntity<?> res = TestUtilities.makeTestPOST("Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==",
                timeTable, this.restTemplate, apiURL);
        @SuppressWarnings("rawtypes")
        String title = (String) ((LinkedHashMap) Objects.requireNonNull(res.getBody())).get("title");
        @SuppressWarnings("rawtypes")
        Integer id = (Integer) ((LinkedHashMap) Objects.requireNonNull(res.getBody())).get("id");

        Assertions.assertEquals(res.getStatusCode().series(), HttpStatus.Series.SUCCESSFUL);
        Assertions.assertEquals(timeTable.getTitle(), title);

        /*
         * remove timeTable
         */
        this.restTemplate.delete(apiURL + id.toString());
    }

    @Test
    public void updateTimeTableTest() {
        TimeTable timeTable = new TimeTable();
        timeTable.setTitle("HR API Online-marathon 2020");
        /*
         * test valid query
         */
        ResponseEntity<?> res = TestUtilities.makeTestPOST("Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==",
                timeTable, this.restTemplate, url + port + "/api/timetables/8");
        @SuppressWarnings("rawtypes")
        String title = (String) ((LinkedHashMap) Objects.requireNonNull(res.getBody())).get("title");

        Assertions.assertEquals(HttpStatus.Series.SUCCESSFUL, res.getStatusCode().series());
        Assertions.assertEquals(timeTable.getTitle(), title);
        /*
         * test bad query
         */
        ResponseEntity<?> resError = TestUtilities.makeTestPOST("Basic YWRtaW5AZ29vZ2xlLmNvbTphZG1pbl9wYXNzdw==",
                timeTable, this.restTemplate, url + port + "/api/timetables/-1");

        Assertions.assertEquals(resError.getStatusCode().series(), HttpStatus.Series.CLIENT_ERROR);
    }
}
