package com.vkrylov.springboottimetable.rest;

import com.vkrylov.springboottimetable.entity.*;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class SpringRestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry corsRegistry) {
        config.exposeIdsFor(TimeTable.class, User.class, Order.class, Attribute.class,
                AttributeValue.class, Notification.class, Session.class);
    }
}
