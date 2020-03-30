package com.example.asynctracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@EnableScheduling
@Service
public class FooService {

    Logger log = LoggerFactory.getLogger(FooService.class);

    @Async
    public void doSomethingAsynchronous() throws InterruptedException {
        Thread.sleep(1000);
        log.info("Hello from async method tid : " + Thread.currentThread().getId());
    }
    @Async
    public CompletableFuture<SomeBody> doCompAsync(){
        SomeBody someBody = new SomeBody();
        someBody.setName("111");

        log.info("Hello from FUTURES async method tid : " + Thread.currentThread().getId());
        return CompletableFuture.completedFuture(someBody);
    }

    @Scheduled(initialDelay = 3000, fixedDelay = 500)
    public void dosmth() {
        log.info("Hello from Scheduler method tid : " + Thread.currentThread().getId());
    }

}
