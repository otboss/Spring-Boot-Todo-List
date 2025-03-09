package com.otboss.todo.service;

import java.time.Duration;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

public class ConsumerService {
        private final Properties consumerProperties;

    @Autowired
    public ConsumerService(Properties consumerProperties) {
        this.consumerProperties = consumerProperties;
    }

    public void consumeMessages(String topic) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
        consumer.subscribe(java.util.Collections.singletonList(topic));

        try {
            while (true) {  // Run indefinitely
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    System.out.println("Received: " + record.value());
                });
            }
        } finally {
            consumer.close();  // Close the consumer when the loop exits
        }
    }
}
