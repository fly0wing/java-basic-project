package com.dmall.order.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
public class OrderEvent {

    private UUID orderId;
    private OrderEventEnum orderEvent;

    public OrderEvent() {
    }

    public static enum OrderEventEnum {
        Submitted, Created, Paid, Cancel
    }
}
