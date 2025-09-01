package com.nayan.api.pedidos.listener.dto;

import java.util.List;

public record OrderCreatedEvent(Long orderId, Long customerId, List<OrderItemEvent> itens) {

}
