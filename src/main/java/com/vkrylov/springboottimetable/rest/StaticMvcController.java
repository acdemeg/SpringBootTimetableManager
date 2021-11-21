package com.vkrylov.springboottimetable.rest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticMvcController implements ErrorController {
    @SuppressWarnings("SpringMVCViewInspection")
    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/error")
    public String redirect() {
        return "forward:/";
    }
}
