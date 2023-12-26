package com.abdullah.exception;

import org.apache.kafka.common.KafkaException;

public class KafkaPublisherException extends KafkaException {
    public KafkaPublisherException(String message){
        super(message);
    }
}