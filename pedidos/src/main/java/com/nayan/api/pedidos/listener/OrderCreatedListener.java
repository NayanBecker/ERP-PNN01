package com.nayan.api.pedidos.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.nayan.api.pedidos.config.RabbitMqConfig.ORDER_CREATED_QUEUE;
import com.nayan.api.pedidos.listener.dto.OrderCreatedEvent;
import com.nayan.api.pedidos.services.OrderService;

@Component
public class OrderCreatedListener {

    private final OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        OrderCreatedEvent event = message.getPayload();
        logger.info("Received order created event: {}", event);

        orderService.saveOrder(message.getPayload());
    }

}