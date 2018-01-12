package com.dmall.order.domain.model;

import java.util.List;
import java.util.UUID;

public interface OrderEventRepository {

    OrderEvent findByOrderIdLast(UUID orderId);

    List<OrderEvent> findByOrderIdAll(UUID orderId);

    public boolean save(OrderEvent order);
}
