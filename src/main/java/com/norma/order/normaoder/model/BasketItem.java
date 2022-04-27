package com.norma.order.normaoder.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class BasketItem extends BaseModel {

    //@ManyToOne(optional = false)
    @ManyToOne()
    private Basket basket;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product product;

    @Column(nullable = false)
    private BigDecimal quantity = BigDecimal.ZERO;


    @Column(nullable = false)
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal discountPrice = BigDecimal.ZERO;
    private BigDecimal taxPrice = BigDecimal.ZERO;
    private BigDecimal shippingPrice = BigDecimal.ZERO;

}
