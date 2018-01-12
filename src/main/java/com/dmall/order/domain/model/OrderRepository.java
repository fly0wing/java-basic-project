package com.dmall.order.domain.model;

import java.util.UUID;

public interface OrderRepository {
    public Order findById(UUID orderId);

    public void save(Order order);
}
