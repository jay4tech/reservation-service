package com.example.reservation.util;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitHelper {

    @Value("${queue.name.reservation}")
    private String reservationQueue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue reservationQueue() {
        return new Queue(reservationQueue, true);
    }
    public void sendReservationNotification(String reservationDetails) {
        rabbitTemplate.convertAndSend(reservationQueue().getName(), reservationDetails);
    }

}