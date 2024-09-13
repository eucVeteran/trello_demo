package com.example.kafka;

import com.example.kafka.messages.KafkaEvent;
import com.example.model.User;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Azizbek Toshpulatov
 */
@Service
public class KafkaProducer {
    private final KafkaTemplate<String, KafkaEvent<User>> kafkaUserTemplate;
    private final KafkaTemplate<String, KafkaEvent<List<User>>> kafkaUserListTemplate;
    private final KafkaTemplate<String, KafkaEvent<String>> kafkaStringTemplate;

    public KafkaProducer(KafkaTemplate<String, KafkaEvent<User>> kafkaUserTemplate,
                         KafkaTemplate<String, KafkaEvent<List<User>>> kafkaUserListTemplate,
                         KafkaTemplate<String, KafkaEvent<String>> kafkaStringTemplate) {
        this.kafkaUserTemplate = kafkaUserTemplate;
        this.kafkaUserListTemplate = kafkaUserListTemplate;
        this.kafkaStringTemplate = kafkaStringTemplate;
    }

    public void sendUser(User user, UUID commandID) {
        KafkaEvent<User> event = new KafkaEvent<>(commandID, user);

        var message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "User")
                .build();

        kafkaUserTemplate.send(message);
    }

    public void sendUserList(List<User> users, UUID commandID) {
        KafkaEvent<List<User>> event = new KafkaEvent<>(commandID, users);

        Message<KafkaEvent<List<User>>> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "Users")
                .build();

        kafkaUserListTemplate.send(message);
    }

    public void sendString(String data, UUID commandID) {
        KafkaEvent<String> event = new KafkaEvent<>(commandID, data);

        Message<KafkaEvent<String>> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "String")
                .build();

        kafkaStringTemplate.send(message);
    }
}