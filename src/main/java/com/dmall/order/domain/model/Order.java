package com.dmall.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;

@AllArgsConstructor
@RequiredArgsConstructor
public class Order implements Cloneable {
    @NonNull
    @Getter
    private UUID id;
    @NonNull
    @Getter
    private Date createdDate;
    @NonNull
    @Getter
    private CustomerContact customerContact;
    @NonNull
    @Getter
    private OrderItem[] orderItems;

    private List<OrderEvent.OrderEventEnum> orderEvents = new ArrayList<>();

    public OrderEvent.OrderEventEnum getStatus() {
        return orderEvents.get(orderEvents.size() - 1);
    }

    public List<OrderEvent.OrderEventEnum> getStatusRoad() {
        return Collections.unmodifiableList(orderEvents);
    }

    public void addStatus(OrderEvent.OrderEventEnum submitted) {
        orderEvents.add(submitted);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * fixme 用于解决Map存储中，对象引用一直存在的问题，是否有其他方案解决？
     *
     * @return
     */
    public Order cloneForMemDB() {
        Order clone = (Order) clone();
        clone.orderEvents = new ArrayList<>();
        return clone;
    }

}
