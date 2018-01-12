package com.dmall.order.impl;

import com.dmall.order.domain.model.OrderEvent;
import com.dmall.order.domain.model.OrderEventRepository;
import com.dmall.order.impl.OrderEventRepositoryImpl;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class OrderEventRepositoryTest {

    @Test
    public void name() throws Exception {
        OrderEventRepository orderEventRepository = new OrderEventRepositoryImpl();
        OrderEvent orderEvent = new OrderEvent(UUID.randomUUID(), OrderEvent.OrderEventEnum.Created);
        assertTrue(orderEventRepository.save(orderEvent));
        OrderEvent byId = orderEventRepository.findByOrderIdLast(orderEvent.getOrderId());
        assertEquals(orderEvent.getOrderId(), byId.getOrderId());
    }
}