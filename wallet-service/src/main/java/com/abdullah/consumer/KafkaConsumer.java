package com.abdullah.consumer;


import com.abdullah.dto.UserKafkaPayload;
import com.abdullah.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {
    private final WalletService walletService;

    public KafkaConsumer(WalletService walletService) {
        this.walletService = walletService;
    }
    @KafkaListener(topics = "${spring.kafka.topics.user-created.topicName}",groupId = "${spring.kafka.topics.consumerGroup}"
    ,containerFactory = "concurrentKafkaListenerContainerFactory")
    public void kafkaListener(@Payload UserKafkaPayload payload){
        try {
            log.info(String.format("Event received = %s",payload));
            walletService.createWallet(payload);
        }catch (KafkaException exception){
            log.error(String.format("Kafka exception = %s",exception.getMessage()));
        }
    }
}
