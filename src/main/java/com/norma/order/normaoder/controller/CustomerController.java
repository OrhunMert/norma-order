package com.norma.order.normaoder.controller;

import com.norma.order.normaoder.dto.GetBasketDTO;
import com.norma.order.normaoder.dto.GetCustomerAllDTO;
import lombok.RequiredArgsConstructor;
import com.norma.order.normaoder.dto.CustomerDTO;
import com.norma.order.normaoder.exception.ControllerOperationException;
import com.norma.order.normaoder.service.CustomerService;
import com.norma.order.normaoder.validator.CreateCustomerValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final CreateCustomerValidator createCustomerValidator;
    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerDTO customerDTO) {
        createCustomerValidator.validate(customerDTO);
        customerService.create(customerDTO);
        return ResponseEntity.ok().build();
    }
    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        if (id < 0) {
            throw new ControllerOperationException.CustomerIDNotValidException("Customer ID should be greater than zero.");
        }

        return ResponseEntity.ok(customerService.getCustomer(id));
    }
    //get all customer.
    @GetMapping()
    public ResponseEntity<Collection<GetCustomerAllDTO>> getAllCustomer(){

        return ResponseEntity.ok(customerService.getAllCustomer());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){

        customerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/basket/{id}")
    public ResponseEntity<GetBasketDTO> getBasket(@PathVariable Long id){

        return ResponseEntity.ok().body(customerService.getBasket(id));
    }

    @PutMapping("/basket/")
    public ResponseEntity<?> addBasketItem(@RequestParam Long id,
                                           @RequestParam Long productId){

        customerService.addBasketItem(id,productId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<GetBasketDTO> orderCustomer(@PathVariable Long id){
        return ResponseEntity.ok().body(customerService.orderCustomer(id));
    }


}
