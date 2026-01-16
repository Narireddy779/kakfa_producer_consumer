package com.example.kafka_consumer.config;

import com.example.kafka_consumer.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {

    private final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    @KafkaListener(topics = "User-Topic", groupId = "user-group")
    public void listener(UserDTO userDTO) {
        logger.info("I am consumer0 from message number {} ", userDTO.toString());
    }

    @KafkaListener(topics = "User-Topic", groupId = "user-group")
    public void listener1(UserDTO userDTO) {
        logger.info("I am consumer1 from message number {} ", userDTO.toString());
    }
}
