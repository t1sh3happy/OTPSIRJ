package org.Evgeniy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/hello")
    public String sayHello() {
        logger.info("Received request for /hello endpoint");
        return "Hello, Spring MVC!";
    }
}