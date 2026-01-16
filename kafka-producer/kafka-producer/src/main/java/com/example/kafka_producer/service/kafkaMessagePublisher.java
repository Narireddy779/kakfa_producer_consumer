package com.example.kafka_producer.service;

import com.example.kafka_producer.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class kafkaMessagePublisher {
    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, Object>> future = template.send("Consumer-New", message);
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("Sent message=[" + message + "] with offset =[" + result.getRecordMetadata().offset() + "]"
                        + "partition  number: " + "[" + result.getRecordMetadata().partition() + "]");
            } else {
                System.out.println("Unable to send message=[" + message + "] due to : " + exception.getMessage());
            }
        });
    }

    public void sendUserMessage(UserDTO userDTO) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("User-Topic", userDTO);
            future.whenComplete(((result, throwable) -> {
                if (throwable == null) {
                    System.out.println("Sent message=[" + userDTO.toString() + "] with offset =[" + result.getRecordMetadata().offset() + "]"
                            + "partition  number: " + "[" + result.getRecordMetadata().partition() + "]");
                } else {
                    System.out.println("Unable to send message=[" + userDTO.toString() + "] due to : " + throwable.getMessage());
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
