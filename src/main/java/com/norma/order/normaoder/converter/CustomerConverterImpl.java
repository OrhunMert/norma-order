package com.norma.order.normaoder.converter;

import com.norma.order.normaoder.dto.CustomerAddressDTO;
import com.norma.order.normaoder.dto.CustomerDTO;
import com.norma.order.normaoder.dto.GetCustomerAllDTO;
import com.norma.order.normaoder.model.Basket;
import com.norma.order.normaoder.model.Customer;
import com.norma.order.normaoder.model.CustomerAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class CustomerConverterImpl implements CustomerConverter {


    //create
    @Override
    public Customer convert(CustomerDTO customerDTO) {

        Customer customer = new Customer();
        customer.setUsername(customerDTO.userName());
        customer.setEmail(customerDTO.email());
        customer.setIdentity(customerDTO.identity());
        customer.setGender(customerDTO.gender());
        customer.setMembership(customerDTO.membership());
        customer.setPassword(customerDTO.password());
        customer.setCreatedAt(new Date());
        customer.setCreatedBy("Orhun Mert");

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setPhoneNumber(customerDTO.customerAddress().phoneNumber());
        customerAddress.setCountry(customerDTO.customerAddress().country());
        customerAddress.setCity(customerDTO.customerAddress().city());
        customerAddress.setPostalCode(customerDTO.customerAddress().postalCode());
        customerAddress.setDescription(customerDTO.customerAddress().description());


        Basket basket = new Basket();
        basket.setPrice(BigDecimal.ZERO);
        basket.setShippingPrice(BigDecimal.ZERO);
        basket.setTaxPrice(BigDecimal.ZERO);
        basket.setTotalPrice(BigDecimal.ZERO);

        customerAddress.setCustomer(customer);
        basket.setCustomer(customer);
        customer.setCustomerAddress(customerAddress);
        customer.setBasket(basket);

        return customer;
    }

    //get by id
    @Override
    public CustomerDTO convert(Customer customer) {

        return new CustomerDTO(customer.getUsername(),
                customer.getEmail(),
                customer.getIdentity(),
                customer.getGender(),
                customer.getPassword(),
                customer.getMembership(),
                convertCustomerAddressDto(customer.getCustomerAddress()));

    }

    //get all list.
    @Override
    public GetCustomerAllDTO getAllCustomer(Customer customer) {
        return new GetCustomerAllDTO(customer.getUsername(),
                customer.getEmail(),
                customer.getIdentity(),
                customer.getGender(),
                customer.getMembership(),
                convertCustomerAddressDto(customer.getCustomerAddress()));
    }


    //get customer adress.
    private CustomerAddressDTO convertCustomerAddressDto(CustomerAddress customerAddress) {
        return new CustomerAddressDTO(customerAddress.getPhoneNumber(),
                customerAddress.getCountry(),
                customerAddress.getCity(),
                customerAddress.getPostalCode(),
                customerAddress.getDescription());
    }
}
