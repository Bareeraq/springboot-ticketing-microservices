package com.ticketsystem.bookingservice.config;

import com.ticketsystem.bookingservice.event.BookingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaWarmup {

    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    public KafkaWarmup(KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void warmUp() {
        log.info("Warming up Kafka producer connection...");
        kafkaTemplate.metrics();
        log.info("Kafka producer warmup complete.");
    }
}