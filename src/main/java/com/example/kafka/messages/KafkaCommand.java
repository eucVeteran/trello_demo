package com.example.kafka.messages;

import com.example.kafka.KafkaActions;

import java.util.UUID;

/**
 * @author Azizbek Toshpulatov
 */
public record KafkaCommand <T> (UUID commandId, T data, KafkaActions action) {}
