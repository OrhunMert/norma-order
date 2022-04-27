package com.norma.order.normaoder.dto;

import com.norma.order.normaoder.model.BasketItem;

import java.math.BigDecimal;
import java.util.Set;

public record GetBasketDTO(BigDecimal price,
                           BigDecimal discountPrice,
                           BigDecimal taxPrice,
                           BigDecimal shippingPrice,
                           BigDecimal totalPrice,
                           Set<BasketItem> items) {
}
