package com.norma.order.normaoder.validator;

import com.norma.order.normaoder.dto.CreateProductDTO;
import com.norma.order.normaoder.dto.GetProductDTO;
import com.norma.order.normaoder.exception.BaseException;
import com.norma.order.normaoder.exception.ControllerOperationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class CreateProductValidatorImp implements CreateProductValidator{
    @Override
    public void validate(CreateProductDTO createProductDTO) throws BaseException {
        // fail first approach.
        if (Objects.isNull(createProductDTO)) {
            throw new ControllerOperationException.CustomerNotValidException("Product can not be null or empty");
        }
        if (!(StringUtils.hasLength(createProductDTO.name()))) {
            throw new ControllerOperationException.ProductNotValidException("Product name can not be null or empty");
        }
        if(Objects.isNull(createProductDTO.price()))
            throw new ControllerOperationException.ProductNotValidException("Product price can not be null or empty");

    }
}
