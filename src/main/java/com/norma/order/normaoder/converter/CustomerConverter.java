package com.norma.order.normaoder.converter;

import com.norma.order.normaoder.dto.CustomerDTO;
import com.norma.order.normaoder.dto.GetCustomerAllDTO;
import com.norma.order.normaoder.model.Customer;

public interface CustomerConverter {

    Customer convert(CustomerDTO customerDTO);

    CustomerDTO convert(Customer customer);

    GetCustomerAllDTO getAllCustomer(Customer customer);
}
