package com.norma.order.normaoder.converter;

import com.norma.order.normaoder.dto.CreateProductDTO;
import com.norma.order.normaoder.dto.GetProductDTO;
import com.norma.order.normaoder.model.Product;

public interface IProductConverter {

    Product createProduct(CreateProductDTO createProductDTO);

    GetProductDTO getProduct(Product product);

    GetProductDTO getAllProductConvert(Product product);
}
