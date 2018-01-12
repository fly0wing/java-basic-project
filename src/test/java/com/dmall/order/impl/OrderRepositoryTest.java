package com.dmall.order.impl;

import com.dmall.order.domain.model.Order;
import com.dmall.order.domain.model.OrderEvent;
import com.dmall.order.domain.model.OrderFactory;
import com.dmall.order.domain.model.OrderRepository;
import com.dmall.order.impl.OrderEventRepositoryImpl;
import com.dmall.order.impl.OrderRepositoryImpl;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class OrderRepositoryTest {
    OrderRepository repository;

    @Before
    public void setUp() throws Exception {
        OrderEventRepositoryImpl orderEventRepository = new OrderEventRepositoryImpl();
        repository = new OrderRepositoryImpl(orderEventRepository);
    }

    @Test
    public void testOrderSave() throws Exception {
        Order order = OrderFactory.createOrder("张三", "北京市通州区");
        repository.save(order);
        Order orderById = repository.findById(order.getId());
        assertEquals(order.getId(), orderById.getId());
    }


    @Test
    public void test_order_status_for_Submitted() throws Exception {
        Order order = OrderFactory.createOrder("张三", "北京市通州区");
        assertEquals(order.getStatus(), OrderEvent.OrderEventEnum.Submitted);
    }

    @Test
    public void test_order_status_for_Created() throws Exception {
        Order order = OrderFactory.createOrder("张三", "北京市通州区");
        repository.save(order);
        assertEquals(order.getStatus(), OrderEvent.OrderEventEnum.Created);
    }

    @Test
    public void test_order_status_for_Created_from_repo() throws Exception {
        Order order = OrderFactory.createOrder("张三", "北京市通州区");
        repository.save(order);
        Order orderById = repository.findById(order.getId());
        assertEquals(orderById.getStatus(), OrderEvent.OrderEventEnum.Created);
    }

    @Test
    public void test_order_StatusRoad_for_Created_from_repo() throws Exception {
        Order order = OrderFactory.createOrder("张三", "北京市通州区");
        repository.save(order);
        Order orderById = repository.findById(order.getId());
        assertEquals(orderById.getStatusRoad(), Lists.newArrayList(OrderEvent.OrderEventEnum.Submitted, OrderEvent.OrderEventEnum.Created));
    }
}