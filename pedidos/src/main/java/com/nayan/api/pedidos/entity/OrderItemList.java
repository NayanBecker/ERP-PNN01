package com.nayan.api.pedidos.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.Data;

@Data
public class OrderItemList {

    private String product;
    private int quantity;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

}