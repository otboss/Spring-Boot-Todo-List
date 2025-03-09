package com.otboss.todo.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final Properties producerProperties;

    @Autowired
    public ProducerService(Properties producerProperties){
        this.producerProperties = producerProperties;
    }

    public void sendMessage(String topic, String message) {
        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProperties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        producer.send(record);
        producer.close();
        System.out.println("Message sent to topic " + topic + ": " + message);
    }
}
