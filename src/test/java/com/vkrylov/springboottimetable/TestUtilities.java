package com.vkrylov.springboottimetable;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public final class TestUtilities {

    public static void setAuthorizationHeader(TestRestTemplate restTemplate, String BasicAuth){
        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add("Authorization", BasicAuth);
                    return execution.execute(request, body);
                }));
    }

    public static HttpStatus.Series makeTestGET(String BasicAuth, TestRestTemplate rest, String url){
        TestUtilities.setAuthorizationHeader(rest, BasicAuth);
        return rest.getForEntity(url, Object.class).getStatusCode().series();
    }

    public static ResponseEntity<?> makeTestPOST(String BasicAuth, Object payload, TestRestTemplate rest, String url){
        TestUtilities.setAuthorizationHeader(rest, BasicAuth);
        return rest.postForEntity(url, payload, Object.class);
    }
}
