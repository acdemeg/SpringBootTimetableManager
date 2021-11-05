package com.vkrylov.springboottimetable;

import org.springframework.boot.test.web.client.TestRestTemplate;

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
}
