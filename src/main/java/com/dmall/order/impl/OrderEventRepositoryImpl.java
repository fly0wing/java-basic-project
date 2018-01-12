package com.dmall.order.impl;

import com.dmall.order.domain.model.Order;
import com.dmall.order.domain.model.OrderEvent;
import com.dmall.order.domain.model.OrderEventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OrderEventRepositoryImpl implements OrderEventRepository {
    private Map<UUID, List<OrderEvent>> storage = new ConcurrentHashMap<UUID, List<OrderEvent>>();

    public OrderEvent findByOrderIdLast(UUID orderId) {
        List<OrderEvent> orderEvents = storage.get(orderId);
        return orderEvents.get(orderEvents.size() - 1);
    }

    public List<OrderEvent> findByOrderIdAll(UUID orderId) {
        return storage.get(orderId);
    }

    public boolean save(OrderEvent orderEvent) {
        UUID orderId = orderEvent.getOrderId();
        List<OrderEvent> orderEvents;
        if (storage.containsKey(orderId)) {
            orderEvents = storage.get(orderId);
        } else {
            orderEvents = new ArrayList<>();
            storage.put(orderEvent.getOrderId(), orderEvents);
        }
        orderEvents.add(orderEvent);
        return true;
    }
}
