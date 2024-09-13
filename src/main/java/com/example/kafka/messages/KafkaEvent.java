package com.example.kafka.messages;

import java.util.UUID;

/**
 *
 * @author Azizbek Toshpulatov
 */
public record KafkaEvent <T> (UUID commandId, T data) {}
