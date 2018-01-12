package com.dmall.order.domain.model;

import java.util.Date;
import java.util.UUID;

public class OrderFactory {
    public static Order createOrder(String name, String address) {
        CustomerContact customerContact = new CustomerContact(name, address);
        OrderItem[] orderItems = new OrderItem[]{};
        Order order = new Order(UUID.randomUUID(), new Date(), customerContact, orderItems);
        order.addStatus(OrderEvent.OrderEventEnum.Submitted);
        return order;
    }
}
