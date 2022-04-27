package com.norma.order.normaoder.service;

import com.norma.order.normaoder.converter.BasketConverter;
import com.norma.order.normaoder.dto.GetBasketDTO;
import com.norma.order.normaoder.dto.GetCustomerAllDTO;
import com.norma.order.normaoder.exception.BusinessServiceOperationException;
import com.norma.order.normaoder.model.*;
import com.norma.order.normaoder.repository.BasketRepository;
import com.norma.order.normaoder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.norma.order.normaoder.converter.CustomerConverter;
import com.norma.order.normaoder.dto.CustomerDTO;
import com.norma.order.normaoder.exception.ServiceOperationException;
import com.norma.order.normaoder.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerConverter customerConverter;
    private final CustomerRepository customerRepository;

    private final BasketConverter basketConverter;
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;



    @Override
    public void create(CustomerDTO customerDTO) {

        Customer customer = customerConverter.convert(customerDTO);
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found"));
        if (customer.isDeleted()) {
            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Customer already deleted");
        }

        customer.setDeleted(true);
        customerRepository.save(customer);

    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        //customer is deleted or not ? we need to control it.
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotFoundException("Customer not found"));

        return customerConverter.convert(customer);
    }

    @Override
    public Collection<GetCustomerAllDTO> getAllCustomer() {
        //customer
       return   customerRepository
               .findAllByIsDeleted(false)
               .stream()
               .map(customerConverter::getAllCustomer)
               .toList();

    }

    @Override
    public GetBasketDTO getBasket(Long id) {
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new ServiceOperationException.CustomerNotFoundException("Customer not found!!! for Basket"));

        if(customer.getBasket().isOrdered()){
            customer.getBasket().getItems().clear();
            customer.getBasket().setTotalPrice(BigDecimal.ZERO);
            customer.getBasket().setPrice(BigDecimal.ZERO);
            customer.getBasket().setShippingPrice(BigDecimal.ZERO);
            customer.getBasket().setDiscountPrice(BigDecimal.ZERO);
            customerRepository.save(customer);
        }

        return basketConverter.getBasketConvert(customer.getBasket());

    }

    @Override
    public void addBasketItem(Long id, Long productId) {

        Customer customer = customerRepository.findById(id).
                orElseThrow(()
                        -> new ServiceOperationException.CustomerNotFoundException("Customer not found!!! for add to Basket"));

        Product product = productRepository.findById(productId)
                .orElseThrow(()
                        -> new ServiceOperationException.ProductNotFoundException("Product not found! for add to Basket"));

        if (product.isDeleted())
            new BusinessServiceOperationException.ProductAlreadyDeletedException("Product was deleted!");

        BasketItem basketItem = new BasketItem();

        basketItem.setPrice(product.getPrice());
        basketItem.setProduct(product);
        basketItem.setQuantity(product.getPrice());

        if (customer.getMembership().equals(Membership.NORMAL)) customer.getBasket().setDiscountPrice(
                customer.getBasket().getDiscountPrice().add(BigDecimal.valueOf(100)));
        else if(customer.getMembership().equals(Membership.GOLDEN)) customer.getBasket().setDiscountPrice(
                customer.getBasket().getDiscountPrice().add(BigDecimal.valueOf(300)));
        else if(customer.getMembership().equals(Membership.DIAMOND))  customer.getBasket().setDiscountPrice(
                customer.getBasket().getDiscountPrice().add(BigDecimal.valueOf(500)));

        customer.getBasket().setTotalPrice(customer.getBasket().getTotalPrice().add(basketItem.getPrice()));
        customer.getBasket().setOrdered(false);
        customer.getBasket().getItems().add(basketItem);

        basketRepository.save(customer.getBasket());
        customerRepository.save(customer);

    }

    @Override
    public GetBasketDTO orderCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.CustomerNotFoundException("Customer not found for order!!!"));

        customer.getBasket().setShippingPrice(
                customer.getBasket().getTotalPrice().add(customer.getBasket().getDiscountPrice().negate()));

        customer.getBasket().setOrdered(true);

        customerRepository.save(customer);

        return basketConverter.getBasketConvert(customer.getBasket());

    }


}
