package com.dmall.order.impl;

import com.dmall.common.Transactional;
import com.dmall.order.domain.model.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OrderRepositoryImpl implements OrderRepository {
    private Map<UUID, Order> storage = new ConcurrentHashMap<UUID, Order>();
    private OrderEventRepository orderEventRepository;

    public OrderRepositoryImpl(OrderEventRepository orderEventRepository) {
        this.orderEventRepository = orderEventRepository;
    }

    public Order findById(UUID orderId) {
        Order order = findOrder(orderId);
        List<OrderEvent> byOrderIdAll = orderEventRepository.findByOrderIdAll(orderId);
        byOrderIdAll.forEach(orderEvent -> order.addStatus(orderEvent.getOrderEvent()));
        return order;
    }

    private Order findOrder(UUID orderId) {
        Order order = storage.get(orderId);
        return order.cloneForMemDB();
    }

    // fixme 在 Repository 层标注事务？还是提到Service层？
    // fixme orderEvent 在Repository层保存？还是提到Service层？
    @Transactional
    public void save(Order order) {
        storage.put(order.getId(), order);
        order.addStatus(OrderEvent.OrderEventEnum.Created);
        for (OrderEvent.OrderEventEnum orderEvent : order.getStatusRoad()) {
            orderEventRepository.save(new OrderEvent(order.getId(), orderEvent));
        }
    }
}
