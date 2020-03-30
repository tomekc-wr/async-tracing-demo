package com.example.asynctracing;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestProducer {
    public static void main(String[] args) {
        Map<String, Object> amountDueProducerProperties = new HashMap<>();
        amountDueProducerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        amountDueProducerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        amountDueProducerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        DefaultKafkaProducerFactory<String, SomeBody> pf = new DefaultKafkaProducerFactory<>(
                amountDueProducerProperties);
        KafkaTemplate<String, SomeBody> template = new KafkaTemplate<>(pf, true);
        template.setDefaultTopic("tests.topic1");
        SomeBody someBody = new SomeBody();
        someBody.setName("Jan");
        ProducerRecord<String, SomeBody> amountDueCreatedDTOProducerRecord = new ProducerRecord<>(
                "tests.topic1", UUID.randomUUID().toString(), someBody);
        template.send(amountDueCreatedDTOProducerRecord);
    }
}
