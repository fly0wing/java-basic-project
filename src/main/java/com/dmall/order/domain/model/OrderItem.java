package com.dmall.order.domain.model;

import lombok.Getter;

@Getter
public class OrderItem {
    private Integer amount;
    private ProductSnapshot productSnapshot;
}
