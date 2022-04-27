package com.norma.order.normaoder.service;

import com.norma.order.normaoder.dto.CreateProductDTO;
import com.norma.order.normaoder.dto.GetProductDTO;

import java.util.Collection;

public interface IProductService {

    void createProduct(CreateProductDTO createProductDTO);

    GetProductDTO getProduct(Long id);

    Collection<GetProductDTO> getAllProduct();

    void deleteProduct(Long id);
}
