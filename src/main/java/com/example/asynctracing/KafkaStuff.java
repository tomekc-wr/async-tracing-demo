package com.example.asynctracing;

import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class KafkaStuff {

    Logger log = LoggerFactory.getLogger(KafkaStuff.class);

    @Bean
    public Consumer<KStream<String, SomeBody>> someConsumer(FooService fooService) {
        return (someBody -> {
            someBody.foreach((k, v) -> {
                log.info("Hello from Kafka Streams consumer tid : " + Thread.currentThread().getId());
                try {
                    fooService.doSomethingSync();
                    fooService.doSomethingAsynchronous();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
    }
}
