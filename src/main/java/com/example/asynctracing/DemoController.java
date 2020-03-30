package com.example.asynctracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class DemoController {

    Logger log = LoggerFactory.getLogger(DemoController.class);

    private final FooService fooService;

    public DemoController(FooService fooService) {
        this.fooService = fooService;
    }

    @GetMapping
    public String foo() throws InterruptedException {
        log.info("Hello from controller tid : " + Thread.currentThread().getId());
        fooService.doSomethingAsynchronous("");
        return "Hello";
    }

}
