package com.ticketsystem.orderservice.service;

import com.ticketsystem.bookingservice.event.BookingEvent;
import com.ticketsystem.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.ticketsystem.orderservice.entity.Order;

@Service
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @KafkaListener(topics= "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent) {
        log.info("Record order event: {}", bookingEvent);

        //CREATE ORDER OBJ FOR DB
        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);
    }

    private Order createOrder(BookingEvent bookingEvent){
        return Order.builder()
                .customerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }
}
