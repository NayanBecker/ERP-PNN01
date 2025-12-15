package com.pnn.api.pedidos.controller.dto;

import java.math.BigDecimal;

import com.pnn.api.pedidos.entity.OrderEntity;

public record OrderResponse(Long orderId, Long customerId, BigDecimal total) {

    public static OrderResponse fromEntity(OrderEntity order) {
        return new OrderResponse(
                order.getOrderId(),
                order.getCustomerId(),
                order.getTotalAmount());
    }
}
