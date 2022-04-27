package com.norma.order.normaoder.converter;

import com.norma.order.normaoder.dto.CreateProductDTO;
import com.norma.order.normaoder.dto.GetProductDTO;
import com.norma.order.normaoder.model.Brand;
import com.norma.order.normaoder.model.Category;
import com.norma.order.normaoder.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements IProductConverter {

    @Override
    public Product createProduct(CreateProductDTO createProductDTO) {

        //We need to add parent name of category.

        Product product = new Product();
        Brand brand = new Brand();
        Category category = new Category();

        product.setName(createProductDTO.name());
        product.setPrice(createProductDTO.price());
        product.setBarcode(createProductDTO.barcode());
        product.setImage(createProductDTO.image());

        brand.setName(createProductDTO.brand().getName());
        category.setName(createProductDTO.category().getName());

        product.setBrand(brand);
        product.setCategory(category);

        return product;

    }

    @Override
    public GetProductDTO getProduct(Product product) {
        return new GetProductDTO(product.getName(),
                product.getPrice(),
                product.getBarcode(),
                product.getImage(),
                product.getBrand(),
                product.getCategory());
    }

    @Override
    public GetProductDTO getAllProductConvert(Product product) {
        return new GetProductDTO(product.getName(),
                product.getPrice(),
                product.getBarcode(),
                product.getImage(),
                product.getBrand(),
                product.getCategory());
    }

}
