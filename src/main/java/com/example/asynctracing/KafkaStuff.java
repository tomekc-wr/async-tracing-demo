package com.example.asynctracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class KafkaStuff {

    Logger log = LoggerFactory.getLogger(KafkaStuff.class);

    @Bean
    public Consumer<SomeBody> someConsumer(FooService fooService) {
        return (someBody -> {
            log.info("Hello from consumer tid : " + Thread.currentThread().getId());
            try {
                fooService.doSomethingAsynchronous();
                fooService.doCompAsync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
