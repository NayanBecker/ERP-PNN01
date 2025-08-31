package com.nayan.api.pedidos.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemList {
    private String product;
    private int quantity;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;
}