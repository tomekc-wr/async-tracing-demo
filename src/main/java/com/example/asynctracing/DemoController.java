package com.example.asynctracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoController {

    Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    FooService fooService;

    @GetMapping
    public String foo() {
        log.info("Hello from controller");
        fooService.doSomethingAsynchronous();
        return "Hello";
    }

}
