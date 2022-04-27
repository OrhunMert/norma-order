package com.norma.order.normaoder.validator;

import com.norma.order.normaoder.dto.CreateProductDTO;
import com.norma.order.normaoder.dto.GetProductDTO;
import com.norma.order.normaoder.exception.BaseException;

public interface CreateProductValidator {
    void validate(CreateProductDTO createProductDTO) throws BaseException;
}
