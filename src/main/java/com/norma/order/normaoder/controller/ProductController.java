package com.norma.order.normaoder.controller;

import com.norma.order.normaoder.dto.CreateProductDTO;
import com.norma.order.normaoder.dto.GetProductDTO;
import com.norma.order.normaoder.service.IProductService;
import com.norma.order.normaoder.validator.CreateProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final CreateProductValidator productValidator;
    private final IProductService productService;


    @PostMapping()
    public ResponseEntity<CreateProductDTO> createProduct(@RequestBody CreateProductDTO createProductDTO){

        //we need validator.
        productValidator.validate(createProductDTO);
        productService.createProduct(createProductDTO);

        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetProductDTO> getProduct(@PathVariable Long id){

        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @GetMapping()
    public ResponseEntity<Collection<GetProductDTO>> getAllProduct(){
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletedProduct(@PathVariable Long id){

        productService.deleteProduct(id);

        return ResponseEntity.ok().build();
    }
}
