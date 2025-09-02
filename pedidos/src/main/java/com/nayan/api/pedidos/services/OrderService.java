package com.nayan.api.pedidos.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nayan.api.pedidos.controller.dto.OrderResponse;
import com.nayan.api.pedidos.entity.OrderEntity;
import com.nayan.api.pedidos.entity.OrderItemList;
import com.nayan.api.pedidos.listener.dto.OrderCreatedEvent;
import com.nayan.api.pedidos.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(OrderCreatedEvent event) {

        var orderEntity = new OrderEntity();
        orderEntity.setOrderId(event.orderId());
        orderEntity.setCustomerId(event.customerId());
        orderEntity.setItems(getListOrderItems(event));
        orderEntity.setTotalAmount(getTotal(event));

        orderRepository.save(orderEntity);
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);
        return orders.map(OrderResponse::fromEntity);
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.items().stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<OrderItemList> getListOrderItems(OrderCreatedEvent event) {
        return event.items().stream()
                .map(item -> {
                    var orderItemEntity = new OrderItemList();
                    orderItemEntity.setProduct(item.productId().toString());
                    orderItemEntity.setQuantity(item.quantity());
                    orderItemEntity.setPrice(item.price());
                    return orderItemEntity;
                })
                .toList();
    }
}
