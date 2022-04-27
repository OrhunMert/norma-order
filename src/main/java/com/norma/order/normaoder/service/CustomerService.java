package com.norma.order.normaoder.service;

import com.norma.order.normaoder.dto.CustomerDTO;
import com.norma.order.normaoder.dto.GetBasketDTO;
import com.norma.order.normaoder.dto.GetCustomerAllDTO;

import java.util.Collection;

public interface CustomerService {

    void create(CustomerDTO customerDTO);

    void delete(Long id);

    CustomerDTO getCustomer(Long id);

    Collection<GetCustomerAllDTO> getAllCustomer();
    //

    GetBasketDTO getBasket(Long id);

    void addBasketItem(Long id , Long productId);

    GetBasketDTO orderCustomer(Long id);



}
