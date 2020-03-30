package com.example.asynctracing;

import brave.kafka.streams.KafkaStreamsTracing;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class KafkaStuff {

    Logger log = LoggerFactory.getLogger(KafkaStuff.class);

    @Autowired
    KafkaStreamsTracing kafkaStreamsTracing;

    @Bean
    public Consumer<KStream<String, SomeBody>> someConsumer(FooService fooService) {
        return (someBody -> {
            someBody
                    .foreach((k, v) -> kafkaStreamsTracing.peek("foo_span", (key, value) -> {
                        log.info("Hello from inside of Kafka stream");
                        try {
                            fooService.doSomethingSync();
                            fooService.doSomethingAsynchronous("InsideTracedPeek");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }));
        });
    }
}
