package com.norma.order.normaoder.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Basket extends BaseModel {

    @Column(nullable = false)
    private BigDecimal price;

    private BigDecimal discountPrice = BigDecimal.ZERO;
    private BigDecimal taxPrice = BigDecimal.ZERO;
    private BigDecimal shippingPrice = BigDecimal.ZERO;
    private boolean isOrdered;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<BasketItem> items = new HashSet<>();

    @OneToOne
    private Customer customer;

}
