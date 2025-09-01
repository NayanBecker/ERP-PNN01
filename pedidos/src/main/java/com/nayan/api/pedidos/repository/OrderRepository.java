package com.nayan.api.pedidos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nayan.api.pedidos.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {

}
