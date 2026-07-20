package com.ticketsystem.bookingservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingEvent {
    private Long userId;
    private Long EventId;
    private Long ticketCount;
    private BigDecimal totalPrice;
}
