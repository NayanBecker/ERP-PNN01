
package com.nayan.api.pedidos.listener.dto;

import java.math.BigDecimal;

public record OrderItemEvent(Long productId, Integer quantity, BigDecimal price) {

}
