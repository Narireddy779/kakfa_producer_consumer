package com.example.kafka_producer.controller;

import com.example.kafka_producer.dto.UserDTO;
import com.example.kafka_producer.service.kafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/producer-app")
@RestController
public class KafkaController {

    @Autowired
    private kafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("/message")
    public ResponseEntity<String> publishMessage(@RequestParam String message) {
        for (int i = 0; i < 10000; i++) {
            kafkaMessagePublisher.sendMessage(message + " :" + i);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Message Published Successfully...");
    }

    @PostMapping("/users")
    public ResponseEntity<String> publishUserMessage(@RequestBody UserDTO userDTO) {
        kafkaMessagePublisher.sendUserMessage(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body("User Message Published Successfully...");
    }
}
