package com.example.kafka_producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateTopic {

    @Bean
    public NewTopic newTopic() {
        return new NewTopic("User-Topic", 4, (short) 1);
    }
}
