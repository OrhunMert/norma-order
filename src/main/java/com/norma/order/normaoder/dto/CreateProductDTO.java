package com.norma.order.normaoder.dto;

import com.norma.order.normaoder.model.Brand;
import com.norma.order.normaoder.model.Category;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductDTO (String name ,
                                BigDecimal price,
                                UUID barcode,
                                String image,
                                Brand brand,
                                Category category){
}
