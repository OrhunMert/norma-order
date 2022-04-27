package com.norma.order.normaoder.service;

import com.norma.order.normaoder.converter.ProductConverter;
import com.norma.order.normaoder.dto.CreateProductDTO;
import com.norma.order.normaoder.dto.GetProductDTO;
import com.norma.order.normaoder.exception.BusinessServiceOperationException;
import com.norma.order.normaoder.exception.ServiceOperationException;
import com.norma.order.normaoder.model.Product;
import com.norma.order.normaoder.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;



@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{

    private final ProductConverter productConverter;
    private final ProductRepository productRepository;

    @Override
    public void createProduct(CreateProductDTO createProductDTO) {

        Product product = productConverter.createProduct(createProductDTO);
        productRepository.save(product);

    }

    @Override
    public GetProductDTO getProduct(Long id){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.ProductNotFoundException("Product not found"));

        return productConverter.getProduct(product);

    }

    @Override
    public Collection<GetProductDTO> getAllProduct() {
        return productRepository
                .findAllByIsDeleted(false)
                .stream()
                .map(productConverter::getAllProductConvert)
                .toList();

    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ServiceOperationException.ProductNotFoundException("Product not found"));
        if(product.isDeleted()){
            throw new BusinessServiceOperationException.ProductAlreadyDeletedException("Product already deleted");
        }
        product.setDeleted(true);
        productRepository.save(product);

    }
}
