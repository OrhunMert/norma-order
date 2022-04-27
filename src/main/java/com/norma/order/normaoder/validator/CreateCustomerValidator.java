package com.norma.order.normaoder.validator;

import com.norma.order.normaoder.dto.CustomerDTO;
import com.norma.order.normaoder.exception.BaseException;

public interface CreateCustomerValidator {
    void validate(CustomerDTO customerDTO) throws BaseException;
}
