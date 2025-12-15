package com.pnn.api.pedidos.services;

import java.math.BigDecimal;
import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.pnn.api.pedidos.controller.dto.OrderResponse;
import com.pnn.api.pedidos.entity.OrderEntity;
import com.pnn.api.pedidos.entity.OrderItemList;
import com.pnn.api.pedidos.listener.dto.OrderCreatedEvent;
import com.pnn.api.pedidos.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
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

    public BigDecimal findTotalOnOrderByCustomerId(Long customerId) {
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                Aggregation.group().sum("totalAmount").as("totalAmount"));

        var response = mongoTemplate.aggregate(aggregations, "tb_orders", Document.class).getUniqueMappedResult();

        return new BigDecimal(response.get("totalAmount").toString());

    }

    private static List<OrderItemList> getListOrderItems(OrderCreatedEvent event) {
        return event.items().stream()
                .map(item -> {
                    var orderItemEntity = new OrderItemList();
                    orderItemEntity.setProduct(item.product());
                    orderItemEntity.setQuantity(item.quantity());
                    orderItemEntity.setPrice(item.price());
                    return orderItemEntity;
                })
                .toList();
    }
}
