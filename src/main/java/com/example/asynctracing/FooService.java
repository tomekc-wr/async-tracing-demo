package com.example.asynctracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    Logger log = LoggerFactory.getLogger(FooService.class);

    @Async
    public void doSomethingAsynchronous() throws InterruptedException {
        Thread.sleep(1000);
        log.info("Hello from async method tid : " + Thread.currentThread().getId());
    }

    public void doSomethingSync() throws InterruptedException {
        log.info("Hello from sync method tid : " + Thread.currentThread().getId());
    }

}
